let buildGridModel;
let applyNodeType;
let prepareGridForSearch;
let validateGridSize;
let findShortestPath;
let heuristic;
let reconstructPath;

beforeAll(async () => {
  ({ buildGridModel, applyNodeType, prepareGridForSearch, validateGridSize } = await import(
    "../src/js/grid.js"
  ));
  ({ findShortestPath, heuristic, reconstructPath } = await import("../src/js/pathfinding.js"));
});

describe("grid validation", () => {
  test("accepts integer dimensions inside supported bounds", () => {
    expect(validateGridSize("20", "30")).toEqual({
      isValid: true,
      rows: 20,
      cols: 30,
      message: "",
    });
  });

  test("rejects non-integer and out-of-range dimensions", () => {
    expect(validateGridSize("4", "30").isValid).toBe(false);
    expect(validateGridSize("20.5", "30").isValid).toBe(false);
    expect(validateGridSize("20", "61").isValid).toBe(false);
  });
});

describe("pathfinding utilities", () => {
  test("heuristic computes Manhattan distance correctly", () => {
    expect(heuristic({ row: 0, col: 0 }, { row: 3, col: 4 })).toBe(7);
    expect(heuristic({ row: 5, col: 5 }, { row: 2, col: 1 })).toBe(7);
  });

  test("reconstructPath returns the correct node chain", () => {
    const nodeA = { row: 0, col: 0, parent: null };
    const nodeB = { row: 0, col: 1, parent: nodeA };
    const nodeC = { row: 1, col: 1, parent: nodeB };

    expect(reconstructPath(nodeC)).toEqual([nodeA, nodeB, nodeC]);
  });
});

describe("findShortestPath", () => {
  function createScenario() {
    const grid = buildGridModel(3, 4);
    const startNode = grid[0][0];
    const targetNode = grid[0][3];

    applyNodeType(startNode, "start");
    applyNodeType(targetNode, "target");

    return { grid, startNode, targetNode };
  }

  test("finds a direct route in an empty grid", () => {
    const { grid, startNode, targetNode } = createScenario();

    prepareGridForSearch(grid);

    const result = findShortestPath({
      grid,
      startNode,
      targetNode,
      algorithm: "astar",
    });

    expect(result.found).toBe(true);
    expect(result.distance).toBe(3);
    expect(result.path.map((node) => [node.row, node.col])).toEqual([
      [0, 0],
      [0, 1],
      [0, 2],
      [0, 3],
    ]);
  });

  test("avoids high-traffic cells when a cheaper route exists", () => {
    const { grid, startNode, targetNode } = createScenario();
    applyNodeType(grid[0][1], "traffic");
    applyNodeType(grid[0][2], "traffic");

    prepareGridForSearch(grid);

    const result = findShortestPath({
      grid,
      startNode,
      targetNode,
      algorithm: "ucs",
    });

    expect(result.found).toBe(true);
    expect(result.distance).toBe(5);
    expect(result.path.map((node) => [node.row, node.col])).toEqual([
      [0, 0],
      [1, 0],
      [1, 1],
      [1, 2],
      [1, 3],
      [0, 3],
    ]);
  });

  test("reports failure when walls fully block the target", () => {
    const { grid, startNode, targetNode } = createScenario();
    applyNodeType(grid[0][1], "wall");
    applyNodeType(grid[1][0], "wall");
    applyNodeType(grid[1][1], "wall");
    applyNodeType(grid[1][2], "wall");
    applyNodeType(grid[1][3], "wall");

    prepareGridForSearch(grid);

    const result = findShortestPath({
      grid,
      startNode,
      targetNode,
      algorithm: "astar",
    });

    expect(result.found).toBe(false);
    expect(result.path).toEqual([]);
    expect(result.distance).toBe(Infinity);
  });
});
