# Lesson 16: Nx + Gradle Multi-Module Monorepo

This lesson demonstrates a proper multi-module Gradle project with Nx and pnpm workspaces integration.

## Prerequisites

- Java JDK 11+ (for Gradle)
- Node.js 16+ and pnpm 8+ (for Nx and pnpm workspaces)

## Setup

### 1. Install pnpm (if not installed)
```bash
npm install -g pnpm
```

### 2. Install workspace dependencies
```bash
pnpm install
```

This will install:
- `nx` - Nx CLI
- `@nx/gradle` - Nx Gradle plugin for Gradle integration

### 3. Verify setup
```bash
# Check Nx version
npx nx --version

# Check Gradle version
./gradlew --version
```

## Workspace Structure

This is a **multi-module Gradle project** where `apps` and `libs` are direct subprojects:

```
lesson16-nx-gradle/
├── apps/
│   ├── cli-app/          # Command-line interface
│   └── service-app/      # REST API server
├── libs/
│   ├── core-utils/       # Shared utilities
│   └── data-models/      # Shared data structures
├── build.gradle          # Root build script
├── settings.gradle       # Project structure definition
├── package.json          # pnpm workspace config
├── pnpm-workspace.yaml   # pnpm workspace definition
└── nx.json               # Nx configuration
```

## Key Features

### 1. Multi-Module Gradle Project
- `apps/` and `libs/` are direct Gradle subprojects
- No nested project structure
- Clean separation of concerns

### 2. pnpm Workspaces
- Monorepo package management with pnpm
- Workspace defined in `pnpm-workspace.yaml`
- Shared dependencies across apps and libs

### 3. Nx Integration
- Nx for task orchestration and caching
- Gradle for actual builds and compilation
- Best of both worlds

## Running Examples

### Using Gradle (Direct)

```bash
# Build all projects
./gradlew buildAll

# Test all projects
./gradlew testAll

# Clean all projects
./gradlew cleanAll

# Show dependency graph
./gradlew showDependencyGraph

# Build specific project
./gradlew :apps:cli-app:build
./gradlew :libs:core-utils:build

# Test specific project
./gradlew :apps:service-app:test
./gradlew :libs:data-models:test
```

### Using Nx (via pnpm)

```bash
# Build all projects
pnpm run build

# Test all projects
pnpm run test

# View dependency graph
pnpm run dep-graph
pnpm run graph

# List all projects
pnpm run projects

# Affected builds (after git changes)
pnpm run affected:build
pnpm run affected:test
```

### Using Nx CLI Directly

```bash
# Build all projects
npx nx run-many --target=build --all

# Test all projects
npx nx run-many --target=test --all

# Build specific project
npx nx build apps-cli-app
npx nx build libs-core-utils

# Test specific project
npx nx test apps-service-app
```

## Project Dependencies

```
service-app ──→ core-utils
    ↓              ↓
    └─→ data-models

cli-app ──→ core-utils
```

## Workspace-Level Tasks

- `buildAll`: Build all subprojects
- `testAll`: Test all subprojects
- `cleanAll`: Clean all build artifacts
- `showDependencyGraph`: Display workspace structure

## Benefits

1. **Scalability**: Easy to add new apps and libs
2. **Build Efficiency**: Incremental builds with caching
3. **Dependency Management**: Clear project dependencies
4. **Code Organization**: Apps/libs separation
5. **Package Management**: pnpm workspaces for Node.js dependencies

## Testing

```bash
# Test all
./gradlew testAll

# Test specific
./gradlew :libs:core-utils:test

# Via pnpm
pnpm run test
```

## Caching

Both Gradle and Nx provide caching:

```bash
# First build (compiles everything)
./gradlew buildAll

# Second build (uses cache - instant)
./gradlew buildAll

# Nx also caches
pnpm run build  # First time
pnpm run build  # Cached
```

## Exercises

1. Add a new library in `libs/` and include it in `settings.gradle`
2. Add a new app in `apps/` that uses existing libraries
3. Modify dependencies and observe affected builds
4. Explore Nx dependency graph: `pnpm run graph`

## Resources

- [Gradle Multi-Project Builds](https://docs.gradle.org/current/userguide/multi_project_builds.html)
- [Nx Documentation](https://nx.dev)
- [pnpm Workspaces](https://pnpm.io/workspaces)
