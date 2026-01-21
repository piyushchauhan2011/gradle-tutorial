# Lesson 16: Nx with Gradle

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

From the project root, run these commands:

```bash
# Build entire workspace with caching
./g -p lesson16-nx-gradle buildAll

# Run all tests
./g -p lesson16-nx-gradle testAll

# Run individual app
./g -p lesson16-nx-gradle:apps:service-app run
./g -p lesson16-nx-gradle:apps:cli-app run

# Build specific library
./g -p lesson16-nx-gradle:libs:core-utils build

# View dependency graph
./g -p lesson16-nx-gradle showDependencyGraph

# Test specific project
./g -p lesson16-nx-gradle:libs:core-utils test
./g -p lesson16-nx-gradle:libs:data-models test

# Show info about a project
./g -p lesson16-nx-gradle:apps:service-app showInfo
```

Alternative with `./gradlew`:

```bash
./gradlew -p lesson16-nx-gradle buildAll
./gradlew -p lesson16-nx-gradle:libs:core-utils build
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

## Testing

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

## Caching Demonstration

```bash
# First build (compiles everything)
./g -p lesson16-nx-gradle buildAll

# Second build (uses cache - instant)
./g -p lesson16-nx-gradle buildAll

# After modifying StringUtils.java:
# Only core-utils and dependent projects rebuild
./g -p lesson16-nx-gradle buildAll
```

## Exercises

1. **Add a New Library**: Create `libs/logging` with a `Logger` class
2. **Add a New App**: Create `apps:admin-app` that uses core-utils
3. **Modify Dependencies**: Have cli-app also depend on data-models
4. **Add Tasks**: Create workspace-level tasks for documentation generation
5. **Explore Caching**: Observe build times with `--info` flag

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
