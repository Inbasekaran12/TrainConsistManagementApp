# Smart Traffic Route Optimizer

Smart Traffic Route Optimizer is a browser-based pathfinding visualizer for comparing A* Search and Uniform Cost Search on a configurable road grid. Users can place roadblocks, simulate high-traffic cells, and watch each algorithm compute the cheapest route in real time.

## Live Demo

Production URL: https://inbasekaran12.github.io/TrainConsistManagementApp/

Add this same URL to the repository's GitHub "About" section so recruiters can launch it from the repo header.

## Technologies Used

- HTML5
- CSS3
- JavaScript (ES Modules)
- Jest
- ESLint
- GitHub Actions
- GitHub Pages

## Installation

```bash
npm install
npm start
```

Then open `http://localhost:8080`.

## Project Structure

```text
.
|-- src/
|   |-- index.html
|   |-- js/
|   |   |-- app.js
|   |   |-- config.js
|   |   |-- dom.js
|   |   |-- grid.js
|   |   `-- pathfinding.js
|   `-- styles/
|       `-- main.css
|-- __tests__/
|-- .github/workflows/
|-- docs/
|   `-- architecture.svg
`-- legacy/
    `-- train-consist-java/
```

## Features

- Interactive grid generation with custom board sizes
- A* Search and Uniform Cost Search comparison
- Weighted traffic cells that influence route cost
- Start and target relocation controls
- Responsive static deployment with no backend required

## Architecture Diagram

![Architecture diagram](docs/architecture.svg)

## Data Flow

1. The browser loads the static frontend from GitHub Pages.
2. `app.js` captures user input and updates the grid model.
3. `pathfinding.js` computes the cheapest route using A* or UCS.
4. The UI animates explored nodes and the final route directly in the browser.

## Testing

Run the full automated suite with:

```bash
npm test
```

The suite covers:

- Grid-size validation
- Path reconstruction helpers
- Successful pathfinding on empty grids
- Weighted-routing behavior around traffic cells
- Failure handling when the target is unreachable

## CI/CD Automation

- `.github/workflows/ci.yml` runs linting and tests on every push and pull request to `main`.
- `.github/workflows/pages.yml` deploys the static app to GitHub Pages after changes land on `main`.

## Performance Metrics

- Static source assets are lightweight, keeping the shipped frontend to roughly 20 KB of project code before HTTP compression.
- The search engine uses an in-memory grid model with incremental DOM repainting, which keeps visual updates smooth even on the default 20x30 board.

## API Documentation

This project does not expose a backend API. All routing logic runs client-side in `src/js/pathfinding.js`.

## Commit Message Guide

Use descriptive, action-oriented commits such as:

- `feat: add weighted traffic cells to the grid`
- `fix: validate grid dimensions before rebuilding`
- `refactor: split UI rendering from pathfinding logic`

## Deployment Notes

If the GitHub Pages URL does not load yet, enable Pages for the repository and set the source to GitHub Actions. The workflow in `.github/workflows/pages.yml` is already configured for that setup.
