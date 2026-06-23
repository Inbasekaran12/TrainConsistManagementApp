import { heuristic, reconstructPath } from "./algorithms.mjs";

const DEFAULT_ROWS = 20;
const DEFAULT_COLS = 30;
const gridElement = document.getElementById("grid");
const rowsInput = document.getElementById("rows");
const colsInput = document.getElementById("cols");
const createGridBtn = document.getElementById("create-grid");
const resetBtn = document.getElementById("reset-grid");
const clearWallsBtn = document.getElementById("clear-walls");
const runBtn = document.getElementById("run-search");
const algorithmSelect = document.getElementById("algorithm");
const outputInfo = document.getElementById("output-info");
const searchModeRadios = document.querySelectorAll('input[name="mode"]');
const speedSelect = document.getElementById("speed");
const stepDelay = { slow: 120, normal: 50, fast: 10 };
let rows = DEFAULT_ROWS;
let cols = DEFAULT_COLS;
let grid = [];
let startNode = null;
let targetNode = null;
let currentMode = "wall";
let isRunning = false;

function setRunning(value) {
  isRunning = value;
  createGridBtn.disabled = value;
  resetBtn.disabled = value;
  clearWallsBtn.disabled = value;
  runBtn.disabled = value;
  searchModeRadios.forEach((radio) => {
    radio.disabled = value;
  });
}

function createCell(row, col) {
  const cell = document.createElement("div");
  cell.className = "cell";
  cell.dataset.row = row;
  cell.dataset.col = col;
  cell.addEventListener("click", () => handleCellClick(row, col));
  return cell;
}

function buildGrid() {
  gridElement.innerHTML = "";
  gridElement.style.gridTemplateColumns = `repeat(${cols}, minmax(0, 1fr))`;
  grid = Array.from({ length: rows }, (_, row) => {
    return Array.from({ length: cols }, (_, col) => {
      const cell = {
        row,
        col,
        walkable: true,
        state: "empty",
        g: Infinity,
        f: Infinity,
        parent: null,
      };
      const cellElement = createCell(row, col);
      gridElement.appendChild(cellElement);
      return { ...cell, element: cellElement };
    });
  });

  startNode = grid[0][0];
  targetNode = grid[rows - 1][cols - 1];
  setNodeState(startNode, "start");
  setNodeState(targetNode, "target");
  updateOutput(
    "Click cells to add walls, set start/target, or toggle traffic. Then choose an algorithm and run."
  );
}

function setNodeState(node, state) {
  node.state = state;
  node.element.className = "cell";
  node.element.classList.add(state);
  node.walkable = state !== "wall";
}

function resetGrid() {
  isRunning = false;
  grid.forEach((row) =>
    row.forEach((node) => {
      node.walkable = true;
      node.g = Infinity;
      node.f = Infinity;
      node.parent = null;
      if (node === startNode) {
        setNodeState(node, "start");
      } else if (node === targetNode) {
        setNodeState(node, "target");
      } else {
        setNodeState(node, "empty");
      }
    })
  );
  outputInfo.textContent = "Grid reset. Add walls or switch start/target before running.";
}

function clearWalls() {
  if (isRunning) return;
  grid.forEach((row) =>
    row.forEach((node) => {
      if (node.state === "wall") {
        node.walkable = true;
        setNodeState(node, "empty");
      }
    })
  );
  outputInfo.textContent = "All walls cleared.";
}

function updateOutput(message) {
  outputInfo.textContent = message;
}

function handleCellClick(row, col) {
  if (isRunning) return;
  const node = grid[row][col];
  if (node === startNode || node === targetNode) {
    updateOutput(
      "Cannot edit the start/target nodes directly. Use the controls to reset or change mode."
    );
    return;
  }

  if (currentMode === "start") {
    setNodeState(startNode, "empty");
    startNode = node;
    setNodeState(node, "start");
    updateOutput("Start position updated.");
  } else if (currentMode === "target") {
    setNodeState(targetNode, "empty");
    targetNode = node;
    setNodeState(node, "target");
    updateOutput("Target position updated.");
  } else if (currentMode === "wall") {
    node.walkable = !node.walkable;
    setNodeState(node, node.walkable ? "empty" : "wall");
  }
}

function getNeighbors(node) {
  const directions = [
    { dr: -1, dc: 0 },
    { dr: 1, dc: 0 },
    { dr: 0, dc: -1 },
    { dr: 0, dc: 1 },
  ];
  return directions
    .map(({ dr, dc }) => {
      const r = node.row + dr;
      const c = node.col + dc;
      return r >= 0 && r < rows && c >= 0 && c < cols ? grid[r][c] : null;
    })
    .filter(Boolean)
    .filter((neighbor) => neighbor.walkable || neighbor === targetNode);
}

function highlightPath(path) {
  path.forEach((node) => {
    if (node !== startNode && node !== targetNode) {
      setNodeState(node, "path");
    }
  });
}

function sleep(ms) {
  return new Promise((resolve) => setTimeout(resolve, ms));
}

async function runSearch() {
  if (isRunning) return;
  setRunning(true);
  outputInfo.textContent = "Searching for the best route...";
  const algorithm = algorithmSelect.value;
  const delay = stepDelay[speedSelect.value] ?? 50;

  grid.forEach((row) =>
    row.forEach((node) => {
      node.g = Infinity;
      node.f = Infinity;
      node.parent = null;
      if (node !== startNode && node !== targetNode && node.state !== "wall") {
        setNodeState(node, "empty");
      }
    })
  );

  if (algorithm === "astar") {
    await runAStar(delay);
  } else {
    await runUniformCostSearch(delay);
  }
  setRunning(false);
}

async function runAStar(delay) {
  const openSet = [startNode];
  startNode.g = 0;
  startNode.f = heuristic(startNode, targetNode);

  while (openSet.length > 0) {
    openSet.sort((a, b) => a.f - b.f);
    const current = openSet.shift();

    if (current === targetNode) {
      const path = reconstructPath(current);
      highlightPath(path);
      updateOutput(`Path found! Steps: ${path.length}, explored: ${countExploredNodes()}`);
      return;
    }

    if (current !== startNode && current !== targetNode) setNodeState(current, "closed");

    for (const neighbor of getNeighbors(current)) {
      const tentativeG = current.g + 1;
      if (tentativeG < neighbor.g) {
        neighbor.parent = current;
        neighbor.g = tentativeG;
        neighbor.f = tentativeG + heuristic(neighbor, targetNode);
        if (!openSet.includes(neighbor)) {
          openSet.push(neighbor);
          if (neighbor !== targetNode) setNodeState(neighbor, "open");
        }
      }
    }

    await sleep(delay);
  }

  updateOutput("No path found. Try changing the layout or removing walls.");
}

async function runUniformCostSearch(delay) {
  const queue = [startNode];
  startNode.g = 0;

  while (queue.length > 0) {
    queue.sort((a, b) => a.g - b.g);
    const current = queue.shift();

    if (current === targetNode) {
      const path = reconstructPath(current);
      highlightPath(path);
      updateOutput(`Path found! Steps: ${path.length}, explored: ${countExploredNodes()}`);
      return;
    }

    if (current !== startNode && current !== targetNode) setNodeState(current, "closed");

    for (const neighbor of getNeighbors(current)) {
      const tentativeG = current.g + 1;
      if (tentativeG < neighbor.g) {
        neighbor.parent = current;
        neighbor.g = tentativeG;
        if (!queue.includes(neighbor)) {
          queue.push(neighbor);
          if (neighbor !== targetNode) setNodeState(neighbor, "open");
        }
      }
    }

    await sleep(delay);
  }

  updateOutput("No path found. Try changing the layout or removing walls.");
}

function countExploredNodes() {
  return grid.flat().filter((node) => node.state === "closed" || node.state === "open").length;
}

function attachEventListeners() {
  createGridBtn.addEventListener("click", () => {
    const newRows = Number(rowsInput.value);
    const newCols = Number(colsInput.value);
    if (newRows < 5 || newCols < 5 || newRows > 40 || newCols > 60) {
      updateOutput("Grid dimensions must be between 5x5 and 40x60.");
      return;
    }
    rows = newRows;
    cols = newCols;
    buildGrid();
  });

  resetBtn.addEventListener("click", resetGrid);
  clearWallsBtn.addEventListener("click", clearWalls);
  runBtn.addEventListener("click", runSearch);

  searchModeRadios.forEach((radio) => {
    radio.addEventListener("change", () => {
      currentMode = radio.value;
    });
  });
}

window.addEventListener("DOMContentLoaded", () => {
  attachEventListeners();
  buildGrid();
});
