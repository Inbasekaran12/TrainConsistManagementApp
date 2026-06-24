import { getNeighbors } from "./grid.js";

export function heuristic(a, b) {
  return Math.abs(a.row - b.row) + Math.abs(a.col - b.col);
}

export function reconstructPath(node) {
  const path = [];
  let current = node;

  while (current) {
    path.push(current);
    current = current.parent;
  }

  return path.reverse();
}

function getNodeKey(node) {
  return `${node.row}:${node.col}`;
}

function sortFrontier(frontier, algorithm) {
  frontier.sort((left, right) => {
    const leftScore = algorithm === "astar" ? left.f : left.g;
    const rightScore = algorithm === "astar" ? right.f : right.g;

    if (leftScore !== rightScore) {
      return leftScore - rightScore;
    }

    return left.g - right.g;
  });
}

export function findShortestPath({ grid, startNode, targetNode, algorithm }) {
  const frontier = [startNode];
  const frontierKeys = new Set([getNodeKey(startNode)]);
  const visitedKeys = new Set();
  const explored = [];

  startNode.g = 0;
  startNode.f = heuristic(startNode, targetNode);

  while (frontier.length > 0) {
    sortFrontier(frontier, algorithm);

    const current = frontier.shift();
    const currentKey = getNodeKey(current);
    frontierKeys.delete(currentKey);

    if (visitedKeys.has(currentKey)) {
      continue;
    }

    visitedKeys.add(currentKey);

    if (current === targetNode) {
      return {
        found: true,
        path: reconstructPath(current),
        explored,
        distance: current.g,
      };
    }

    if (current !== startNode) {
      explored.push(current);
    }

    for (const neighbor of getNeighbors(grid, current)) {
      const neighborKey = getNodeKey(neighbor);
      const tentativeDistance = current.g + neighbor.cost;

      if (tentativeDistance >= neighbor.g) {
        continue;
      }

      neighbor.parent = current;
      neighbor.g = tentativeDistance;
      neighbor.f =
        algorithm === "astar"
          ? tentativeDistance + heuristic(neighbor, targetNode)
          : tentativeDistance;

      if (!visitedKeys.has(neighborKey) && !frontierKeys.has(neighborKey)) {
        frontier.push(neighbor);
        frontierKeys.add(neighborKey);
      }
    }
  }

  return {
    found: false,
    path: [],
    explored,
    distance: Infinity,
  };
}
