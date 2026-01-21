# Nx Integration Setup Guide

## Quick Start

### Option 1: Gradle Only (No Installation)
Works immediately without npm:

```bash
# Build all
./g -p lesson16-nx-gradle buildAll

# Test all
./g -p lesson16-nx-gradle testAll

# Show dependency graph
./g -p lesson16-nx-gradle showDependencyGraph
```

### Option 2: Nx + Gradle (After npm install)
For advanced monorepo features:

```bash
# Install dependencies (one-time)
npm install

# Build all projects
npm run build

# Test all projects
npm run test

# View interactive dependency graph
npm run dep-graph

# Only build affected projects after changes
npm run affected:build

# Only test affected projects after changes
npm run affected:test
```

## What Gets Installed

The `npm install` command installs:

```
@nx/cli            - Nx command-line interface
@nx/workspace      - Nx workspace management
@nx/gradle         - Gradle integration plugin
nx                 - Nx core package
```

These packages enable Nx to orchestrate Gradle builds across the entire monorepo.

## Workspace Structure

```
gradle-tutorial/
├── package.json                 # npm dependencies
├── nx.json                      # Nx workspace config
├── .nxrc.json                   # Nx plugin config
├── lesson01-intro/
├── lesson02-setup/
├── ...
└── lesson16-nx-gradle/
    ├── nx.json                  # Project config
    ├── project.json             # Nx project metadata
    ├── build.gradle
    ├── build.gradle.kts
    ├── settings.gradle
    ├── apps/
    │   ├── service-app/
    │   │   └── project.json
    │   └── cli-app/
    │       └── project.json
    └── libs/
        ├── core-utils/
        │   └── project.json
        └── data-models/
            └── project.json
```

## Commands Comparison

| Task             | Gradle (`./g`)                                  | Nx (`npm run`)           |
| ---------------- | ----------------------------------------------- | ------------------------ |
| Build all        | `./g -p lesson16-nx-gradle buildAll`            | `npm run build`          |
| Test all         | `./g -p lesson16-nx-gradle testAll`             | `npm run test`           |
| Dependency graph | `./g -p lesson16-nx-gradle showDependencyGraph` | `npm run dep-graph`      |
| Affected build   | Manual                                          | `npm run affected:build` |
| Affected test    | Manual                                          | `npm run affected:test`  |

## When to Use Each

### Use Gradle (`./g`)
- Quick tests without npm setup
- Specific lesson exercises
- Understanding Gradle fundamentals
- Minimal overhead

### Use Nx (`npm run`)
- Monorepo-scale projects (50+ modules)
- Automatic affected detection
- Interactive dependency visualization
- Team collaboration
- CI/CD pipelines

## Troubleshooting

### npm command not found
- Install Node.js from https://nodejs.org
- Requires Node.js 16+ and npm 8+

### `npm install` fails
- Check your internet connection
- Try `npm cache clean --force` then retry
- Use `npm install --no-optional` as fallback

### Nx commands not working
- Ensure you ran `npm install` first
- Check: `npx nx --version`
- If still failing, try: `npm install -g nx`

### Gradle commands work, Nx doesn't
- That's fine! Nx is optional
- Use `./g -p lesson16-nx-gradle buildAll` instead
- Nx only adds convenience for large monorepos

## Learn More

- [Nx Documentation](https://nx.dev)
- [Nx Gradle Plugin](https://github.com/nrwl/nx/tree/master/packages/gradle)
- [Gradle Documentation](https://docs.gradle.org)
