# Smart Traffic Route Optimizer

A browser-based pathfinding visualization app for interactive route planning and algorithm comparison. The application uses A\* and Uniform Cost Search to demonstrate pathfinding on a configurable grid, with real-time exploration and clear visual feedback.

## Technologies

- HTML
- CSS
- JavaScript (ES Modules)
- GitHub Actions
- Jest
- GitHub Pages

## Installation

```bash
npm install
npm start
```

Open `http://localhost:8080` to launch the app locally.

## Repository Structure

- `src/` - source assets: `index.html`, `styles.css`, `script.js`, `algorithms.mjs`
- `__tests__/` - automated unit tests
- `.github/workflows/` - CI and deployment pipelines
- `.gitignore` - local environment and generated files ignored
- `package.json` - project metadata and scripts

## What’s Included

- Interactive grid editing with start/target placement
- A\* and Uniform Cost Search visualization
- Reset and clear walls controls
- Responsive UI for desktop and mobile
- GitHub Actions CI for automated testing
- GitHub Pages deployment configured
- Architecture diagram included in the repo

## Performance Notes

This frontend-only static app is optimized for speed using minimal DOM updates and CSS transitions. The page size is lightweight and can achieve fast load times on modern browsers.

## Deployment

GitHub Pages is configured to publish the `src/` folder from the `gh-pages` branch. After the first push to `main`, the repository can be published to:

https://bruce12-glitch.github.io/Smart-Traffic-Route-/

Add that URL to the repository About section to make it clickable for recruiters.

## Tests

Run the test suite with:

```bash
npm test
```

## Architecture

![Architecture diagram](architecture.svg)

## Notes

- No backend is required for this application.
- The repository is ready for production deployment as a static site.
