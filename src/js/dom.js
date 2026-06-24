import { getCellClassNames } from "./grid.js";

export function getDomReferences() {
  return {
    gridElement: document.getElementById("grid"),
    rowsInput: document.getElementById("rows"),
    colsInput: document.getElementById("cols"),
    createGridBtn: document.getElementById("create-grid"),
    resetBtn: document.getElementById("reset-grid"),
    clearObstaclesBtn: document.getElementById("clear-walls"),
    runBtn: document.getElementById("run-search"),
    algorithmSelect: document.getElementById("algorithm"),
    speedSelect: document.getElementById("speed"),
    outputInfo: document.getElementById("output-info"),
    modeRadios: document.querySelectorAll('input[name="mode"]'),
  };
}

export function createCellElement(node) {
  const element = document.createElement("button");
  element.type = "button";
  element.className = getCellClassNames(node);
  element.dataset.row = String(node.row);
  element.dataset.col = String(node.col);
  element.setAttribute("aria-label", `Grid cell ${node.row + 1}, ${node.col + 1}`);
  return element;
}

export function renderGrid(gridElement, grid) {
  const fragment = document.createDocumentFragment();
  gridElement.innerHTML = "";
  gridElement.style.gridTemplateColumns = `repeat(${grid[0].length}, minmax(0, 1fr))`;

  grid.forEach((row) => {
    row.forEach((node) => {
      node.element = createCellElement(node);
      fragment.appendChild(node.element);
    });
  });

  gridElement.appendChild(fragment);
}

export function renderNode(node) {
  if (node.element) {
    node.element.className = getCellClassNames(node);
  }
}

export function setControlsDisabled(dom, disabled) {
  dom.createGridBtn.disabled = disabled;
  dom.resetBtn.disabled = disabled;
  dom.clearObstaclesBtn.disabled = disabled;
  dom.runBtn.disabled = disabled;
  dom.rowsInput.disabled = disabled;
  dom.colsInput.disabled = disabled;
  dom.algorithmSelect.disabled = disabled;
  dom.speedSelect.disabled = disabled;
  dom.modeRadios.forEach((radio) => {
    radio.disabled = disabled;
  });
}

export function updateOutput(dom, message) {
  dom.outputInfo.textContent = message;
}
