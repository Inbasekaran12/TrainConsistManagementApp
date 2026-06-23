export function heuristic(a, b) {
  return Math.abs(a.row - b.row) + Math.abs(a.col - b.col);
}

export function reconstructPath(node) {
  const path = [];
  while (node) {
    path.push(node);
    node = node.parent;
  }
  return path.reverse();
}
