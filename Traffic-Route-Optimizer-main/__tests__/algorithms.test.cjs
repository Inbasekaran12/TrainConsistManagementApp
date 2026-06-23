let heuristic;
let reconstructPath;

beforeAll(async () => {
  ({ heuristic, reconstructPath } = await import("../src/algorithms.mjs"));
});

describe("algorithm utilities", () => {
  test("heuristic computes Manhattan distance correctly", () => {
    expect(heuristic({ row: 0, col: 0 }, { row: 3, col: 4 })).toBe(7);
    expect(heuristic({ row: 5, col: 5 }, { row: 2, col: 1 })).toBe(7);
  });

  test("reconstructPath returns the correct node chain", () => {
    const nodeA = { row: 0, col: 0, parent: null };
    const nodeB = { row: 0, col: 1, parent: nodeA };
    const nodeC = { row: 1, col: 1, parent: nodeB };

    const path = reconstructPath(nodeC);

    expect(path).toEqual([nodeA, nodeB, nodeC]);
  });
});
