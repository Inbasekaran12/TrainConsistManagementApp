import { DEFAULT_COLS, DEFAULT_ROWS, STEP_DELAY } from "./config.js";
import {
  applyNodeType,
  buildGridModel,
  clearObstacles,
  prepareGridForSearch,
  updateNodeStatus,
  validateGridSize,
  resetGridTerrain,
} from "./grid.js";
import { getDomReferences, renderGrid, renderNode, setControlsDisabled, updateOutput } from "./dom.js";
import { findShortestPath } from "./pathfinding.js";

const dom = getDomReferences();

const appState = {
  rows: DEFAULT_ROWS,
  cols: DEFAULT_COLS,
  grid: [],
  startNode: null,
  targetNode: null,
  currentMode: "wall",
  isRunning: false,
};

function sleep(delay) {
  return new Promise((resolve) => window.setTimeout(resolve, delay));
}

function syncNode(node, type, status = "") {
  applyNodeType(node, type);
  updateNodeStatus(node, status);
  renderNode(node);
}

function initializeAnchors() {
  appState.startNode = appState.grid[0][0];
  appState.targetNode = appState.grid[appState.rows - 1][appState.cols - 1];
  syncNode(appState.startNode, "start");
  syncNode(appState.targetNode, "target");
}

function buildGrid() {
  appState.grid = buildGridModel(appState.rows, appState.cols);
  renderGrid(dom.gridElement, appState.grid);
  initializeAnchors();
  updateOutput(
    dom,
    "Add walls or traffic, move the endpoints, and run a search to visualize the best route."
  );
}

function repaintGrid() {
  appState.grid.forEach((row) => {
    row.forEach((node) => renderNode(node));
  });
}

function resetGrid() {
  resetGridTerrain(appState.grid, appState.startNode, appState.targetNode);
  repaintGrid();
  updateOutput(dom, "Grid reset. Start adding walls or traffic to create a new scenario.");
}

function clearGridObstacles() {
  clearObstacles(appState.grid, appState.startNode, appState.targetNode);
  repaintGrid();
  updateOutput(dom, "Walls and traffic markers cleared.");
}

function moveAnchor(node, anchorType) {
  const currentAnchor = anchorType === "start" ? appState.startNode : appState.targetNode;

  if (node === currentAnchor) {
    return;
  }

  syncNode(currentAnchor, "empty");

  if (anchorType === "start") {
    appState.startNode = node;
  } else {
    appState.targetNode = node;
  }

  syncNode(node, anchorType);
  updateOutput(dom, `${anchorType === "start" ? "Start" : "Target"} position updated.`);
}

function toggleObstacle(node, obstacleType) {
  const nextType = node.type === obstacleType ? "empty" : obstacleType;
  syncNode(node, nextType);
}

function handleGridClick(event) {
  if (appState.isRunning) {
    return;
  }

  const cell = event.target.closest(".cell");

  if (!cell) {
    return;
  }

  const row = Number(cell.dataset.row);
  const col = Number(cell.dataset.col);
  const node = appState.grid[row][col];

  if (appState.currentMode === "start") {
    if (node === appState.targetNode) {
      updateOutput(dom, "Choose a different cell so start and target stay separate.");
      return;
    }

    moveAnchor(node, "start");
    return;
  }

  if (appState.currentMode === "target") {
    if (node === appState.startNode) {
      updateOutput(dom, "Choose a different cell so start and target stay separate.");
      return;
    }

    moveAnchor(node, "target");
    return;
  }

  if (node === appState.startNode || node === appState.targetNode) {
    updateOutput(dom, "Use Set Start or Set Target mode to move the endpoints.");
    return;
  }

  toggleObstacle(node, appState.currentMode);
}

async function animateSearch(result, delay) {
  for (const node of result.explored) {
    if (node !== appState.startNode && node !== appState.targetNode) {
      updateNodeStatus(node, "closed");
      renderNode(node);
      await sleep(delay);
    }
  }

  if (!result.found) {
    updateOutput(dom, `No path found after exploring ${result.explored.length} nodes.`);
    return;
  }

  const route = result.path.slice(1, -1);

  for (const node of route) {
    updateNodeStatus(node, "path");
    renderNode(node);
    await sleep(delay);
  }

  updateOutput(
    dom,
    `Path found in ${result.distance} cost units after exploring ${result.explored.length} nodes.`
  );
}

async function runSearch() {
  if (appState.isRunning) {
    return;
  }

  appState.isRunning = true;
  setControlsDisabled(dom, true);
  prepareGridForSearch(appState.grid);
  repaintGrid();
  syncNode(appState.startNode, "start");
  syncNode(appState.targetNode, "target");
  updateOutput(dom, "Searching for the best route...");

  const algorithm = dom.algorithmSelect.value;
  const delay = STEP_DELAY[dom.speedSelect.value] ?? STEP_DELAY.normal;
  const result = findShortestPath({
    grid: appState.grid,
    startNode: appState.startNode,
    targetNode: appState.targetNode,
    algorithm,
  });

  await animateSearch(result, delay);

  appState.isRunning = false;
  setControlsDisabled(dom, false);
}

function attachEventListeners() {
  dom.createGridBtn.addEventListener("click", () => {
    const nextSize = validateGridSize(dom.rowsInput.value, dom.colsInput.value);

    if (!nextSize.isValid) {
      updateOutput(dom, nextSize.message);
      return;
    }

    appState.rows = nextSize.rows;
    appState.cols = nextSize.cols;
    buildGrid();
  });

  dom.resetBtn.addEventListener("click", resetGrid);
  dom.clearObstaclesBtn.addEventListener("click", clearGridObstacles);
  dom.runBtn.addEventListener("click", runSearch);
  dom.gridElement.addEventListener("click", handleGridClick);

  dom.modeRadios.forEach((radio) => {
    radio.addEventListener("change", () => {
      appState.currentMode = radio.value;
    });
  });
}

window.addEventListener("DOMContentLoaded", () => {
  attachEventListeners();
  buildGrid();
});
