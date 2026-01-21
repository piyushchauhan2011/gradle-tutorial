# Lesson 16: Nx with Gradle

## Prerequisites

- Java JDK 11+ (for Gradle)
- Node.js 16+ and npm 8+ (for Nx)

## Setup

### 1. Install Nx globally (optional)
```bash
npm install -g nx
```

### 2. Install workspace dependencies
```bash
npm install
```

This will install:
- `nx` - Nx CLI
- `@nrwl/cli` - Nx command line tools
- `@nrwl/gradle` - Nx Gradle plugin for Nx orchestration

### 3. Verify setup
```bash
# Check Nx version
nx --version

# Check Gradle version
./g --version
```

## Overview

This lesson demonstrates how to use **Nx** principles with Gradle to manage a scalable monorepo workspace. Nx provides:

- **Workspace Organization**: Clear separation of apps and libs
- **Dependency Management**: Explicit project dependencies
- **Build Caching**: Efficient incremental builds
- **Task Orchestration**: Coordinated task execution across the workspace

## Workspace Structure

```
lesson16-nx-gradle/
├── apps/
│   ├── service-app/      (REST API server)
│   └── cli-app/          (Command-line interface)
├── libs/
│   ├── core-utils/       (Shared utilities - StringUtils)
│   └── data-models/      (Shared models - User)
├── build.gradle
├── build.gradle.kts
├── settings.gradle
└── settings.gradle.kts
```

## Key Concepts

### 1. Monorepo Organization
- **Apps**: Executable applications (service-app, cli-app)
- **Libs**: Reusable libraries (core-utils, data-models)
- Clear architectural boundaries

### 2. Project Dependencies
```
service-app ──→ core-utils
    ↓              ↓
    └─→ data-models

cli-app ──→ core-utils
```

### 3. Build Caching
Gradle automatically caches task outputs:
- First build: Compiles all projects
- Second build: Uses cached outputs (instant)
- After code change: Only affected projects rebuild

### 4. Workspace-Level Tasks
- `buildAll`: Build all subprojects
- `testAll`: Test all subprojects
- `cleanAll`: Clean all build artifacts
- `showDependencyGraph`: Display workspace structure

## Runnable Examples

### Using Gradle (Direct)

From the project root, run these commands:

```bash
# Build entire workspace with caching
./g -p lesson16-nx-gradle buildAll

# Run all tests
./g -p lesson16-nx-gradle testAll

# View dependency graph
./g -p lesson16-nx-gradle showDependencyGraph

# Test specific project
./g -p lesson16-nx-gradle:libs:core-utils test
./g -p lesson16-nx-gradle:libs:data-models test

# Show info about a project
./g -p lesson16-nx-gradle:apps:service-app showInfo
```

### Using Nx (with npm)

First install Nx dependencies:
```bash
npm install
```

Then run Nx commands from the project root:

```bash
# Build all projects
nx run-many --target=build --all

# Test all projects  
nx run-many --target=test --all

# Build specific project
nx build apps-service-app
nx build libs-core-utils

# Test specific project
nx test apps-cli-app
nx test libs-data-models

# View dependency graph (opens in browser)
nx dep-graph

# Show affected projects after changes
nx affected --target=build
nx affected --target=test
nx affected:dep-graph
```

### npm Scripts (Convenience)

```bash
npm run build          # Build all projects
npm run test           # Test all projects
npm run dep-graph      # View dependency graph
npm run affected:build # Build affected projects
npm run affected:test  # Test affected projects
```

## Nx Benefits with Gradle

### 1. **Scalability**
Easily add new apps and libs without complex configurations.

### 2. **Build Efficiency**
- Incremental builds only touch affected projects
- Shared build outputs across projects
- Parallel task execution

### 3. **Dependency Management**
Explicit `implementation project()` declarations make dependencies clear and enforceable.

### 4. **Code Organization**
- Apps: Place in `apps/` for executable applications
- Libs: Place in `libs/` for reusable code
- Easy to discover and navigate

## Project Details

### Libraries

#### core-utils (libs/core-utils)
Provides string manipulation utilities:
- `StringUtils.capitalize()`: Capitalize first letter
- `StringUtils.reverse()`: Reverse a string

#### data-models (libs/data-models)
Defines shared data structures:
- `User`: User entity with id, name, email

### Applications

#### service-app (apps/service-app)
REST API server that uses:
- `StringUtils` from core-utils
- `User` model from data-models

#### cli-app (apps/cli-app)
Command-line tool that uses:
- `StringUtils` from core-utils

## Why Both Nx and Gradle?

### Gradle's Role
- **Build Execution**: Compiles code, runs tests, packages artifacts
- **Dependency Resolution**: Manages JAR and Maven dependencies
- **Task Management**: Defines and orchestrates build tasks
- **Performance**: Handles actual compilation and testing

### Nx's Role
- **Workspace Management**: Organizes projects into apps/libs structure
- **Task Orchestration**: Distributes and parallelizes Gradle builds
- **Build Caching**: Caches task outputs across the workspace
- **Dependency Tracking**: Identifies affected projects after changes
- **Developer Experience**: Provides unified commands across workspace

### When to Use Each

| Tool   | Use Case                                              | Command                           |
| ------ | ----------------------------------------------------- | --------------------------------- |
| Gradle | Quick builds, single project, raw compilation         | `./g -p lesson16-nx-gradle build` |
| Nx     | Monorepo scale, affected builds, caching optimization | `npm run build`                   |
| Both   | Production monorepo with 50+ projects                 | Both together                     |

## Gradle vs Nx Commands

| Operation | Gradle                                          | Nx                           |
| --------- | ----------------------------------------------- | ---------------------------- |
| Build all | `./g -p lesson16-nx-gradle buildAll`            | `npm run build`              |
| Test all  | `./g -p lesson16-nx-gradle testAll`             | `npm run test`               |
| View deps | `./g -p lesson16-nx-gradle showDependencyGraph` | `npm run dep-graph`          |
| Affected  | Manual tracking                                 | `nx affected --target=build` |
| Parallel  | Limited                                         | Full parallelization         |

## Testing

### Using Gradle
```bash
# Test all projects
./g -p lesson16-nx-gradle testAll

# Test with verbose output
./g -p lesson16-nx-gradle testAll --info

# Test specific library
./g -p lesson16-nx-gradle:libs:core-utils test

# Run specific test
./g -p lesson16-nx-gradle:libs:core-utils test --tests StringUtilsTest.testCapitalize
```

### Using Nx
```bash
# Test all projects
npm run test

# Test specific project
nx test apps-service-app
nx test libs-core-utils

# Test affected projects after changes
npm run affected:test
```

## Caching Demonstration

### With Gradle
```bash
# First build (compiles everything)
./g -p lesson16-nx-gradle buildAll

# Second build (uses cache - instant)
./g -p lesson16-nx-gradle buildAll

# After modifying StringUtils.java:
# Only core-utils and dependent projects rebuild
./g -p lesson16-nx-gradle buildAll
```

### With Nx
```bash
# First build (compiles everything)
npm run build

# Second build (uses Nx cache - instant)
npm run build

# View what's cached
nx dep-graph

# Only rebuild affected after changes
npm run affected:build
```

## Exercises

1. **Add a New Library**: Create `libs/logging` with a `Logger` class, then run `./g -p lesson16-nx-gradle buildAll`
2. **Add a New App**: Create `apps:admin-app` that uses core-utils, then run `nx dep-graph`
3. **Modify Dependencies**: Have cli-app also depend on data-models using `implementation project(":libs:data-models")`
4. **Track Affected**: Modify StringUtils.java and run `npm run affected:build` to see what rebuilds
5. **Explore Caching**: Run `./g -p lesson16-nx-gradle buildAll` twice and observe cache behavior
6. **Use Nx Dashboard**: Run `npm run dep-graph` to visualize the entire dependency graph
7. **Add Custom Tasks**: Add a workspace-level task in build.gradle and orchestrate it with Nx

## Advanced: Nx Integration (Optional)

For production use, consider adding actual Nx:

```bash
npm install -g nx
nx init
```

Nx will provide:
- Advanced caching strategies
- Dependency graph visualization
- Affected task optimization
- Plugin ecosystem

## Key Takeaways

- Gradle supports Nx-style workspace organization
- Explicit project dependencies maintain clear architecture
- Build caching significantly improves build performance
- Workspace-level tasks coordinate multi-project builds
- Monorepo pattern scales well with hundreds of projects

## Resources

- [Gradle Multi-Project Builds](https://docs.gradle.org/current/userguide/multi_project_builds.html)
- [Nx Documentation](https://nx.dev)
- [Gradle Build Cache](https://docs.gradle.org/current/userguide/build_cache.html)
