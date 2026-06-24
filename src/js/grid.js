import { MAX_COLS, MAX_ROWS, MIN_COLS, MIN_ROWS, TRAFFIC_COST } from "./config.js";

function resetSearchMetadata(node) {
  node.g = Infinity;
  node.f = Infinity;
  node.parent = null;
  node.status = "";
}

export function createNode(row, col) {
  return {
    row,
    col,
    type: "empty",
    status: "",
    cost: 1,
    walkable: true,
    g: Infinity,
    f: Infinity,
    parent: null,
  };
}

export function buildGridModel(rows, cols) {
  return Array.from({ length: rows }, (_, row) =>
    Array.from({ length: cols }, (_, col) => createNode(row, col))
  );
}

export function applyNodeType(node, type) {
  node.type = type;
  node.walkable = type !== "wall";
  node.cost = type === "traffic" ? TRAFFIC_COST : 1;

  if (type === "wall") {
    node.status = "";
  }
}

export function updateNodeStatus(node, status) {
  node.status = status;
}

export function prepareGridForSearch(grid) {
  grid.forEach((row) => {
    row.forEach((node) => resetSearchMetadata(node));
  });
}

export function resetGridTerrain(grid, startNode, targetNode) {
  grid.forEach((row) => {
    row.forEach((node) => {
      resetSearchMetadata(node);

      if (node === startNode) {
        applyNodeType(node, "start");
      } else if (node === targetNode) {
        applyNodeType(node, "target");
      } else {
        applyNodeType(node, "empty");
      }
    });
  });
}

export function clearObstacles(grid, startNode, targetNode) {
  grid.forEach((row) => {
    row.forEach((node) => {
      resetSearchMetadata(node);

      if (node === startNode) {
        applyNodeType(node, "start");
      } else if (node === targetNode) {
        applyNodeType(node, "target");
      } else if (node.type === "wall" || node.type === "traffic") {
        applyNodeType(node, "empty");
      }
    });
  });
}

export function getNeighbors(grid, node) {
  const offsets = [
    { row: -1, col: 0 },
    { row: 1, col: 0 },
    { row: 0, col: -1 },
    { row: 0, col: 1 },
  ];

  return offsets
    .map(({ row, col }) => grid[node.row + row]?.[node.col + col] ?? null)
    .filter((neighbor) => Boolean(neighbor) && neighbor.walkable);
}

export function getCellClassNames(node) {
  return ["cell", node.type, node.status].filter(Boolean).join(" ");
}

export function validateGridSize(rowValue, colValue) {
  const rowCount = Number(rowValue);
  const colCount = Number(colValue);
  const isValid =
    Number.isInteger(rowCount) &&
    Number.isInteger(colCount) &&
    rowCount >= MIN_ROWS &&
    rowCount <= MAX_ROWS &&
    colCount >= MIN_COLS &&
    colCount <= MAX_COLS;

  return {
    isValid,
    rows: rowCount,
    cols: colCount,
    message: isValid
      ? ""
      : `Grid dimensions must be whole numbers between ${MIN_ROWS}x${MIN_COLS} and ${MAX_ROWS}x${MAX_COLS}.`,
  };
}
