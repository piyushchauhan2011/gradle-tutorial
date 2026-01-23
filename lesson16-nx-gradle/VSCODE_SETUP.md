# VS Code / Cursor Setup for Nx + Gradle Projects

## Overview

This project uses **Nx** with the **@nx/gradle** plugin to manage a multi-module Gradle monorepo. This guide helps configure VS Code or Cursor IDE for optimal Java development.

## Recommended Extensions

Install these VS Code extensions for the best experience:

1. **Language Support for Java** (`redhat.java`) - Java language support
2. **Gradle for Java** (`vscjava.vscode-gradle`) - Gradle build tool integration
3. **Nx Console** (`nrwl.angular-console`) - Nx workspace visualization and commands

## Project Structure

```
lesson16-nx-gradle/
├── apps/
│   ├── cli-app/          # CLI application
│   └── service-app/      # Service application
├── libs/
│   ├── core-utils/       # Shared utilities library
│   └── data-models/      # Shared data models library
├── build.gradle          # Root Gradle build (container)
├── settings.gradle       # Gradle project settings
├── nx.json               # Nx configuration
└── package.json          # Node.js dependencies (Nx)
```

## Initial Setup

### 1. Install Dependencies

```bash
pnpm install
```

### 2. Build Projects (generates class files for IDE)

```bash
./gradlew buildAll
```

This compiles all Java code and helps the IDE resolve dependencies.

## Java Extension Configuration

If VS Code/Cursor doesn't recognize Java files correctly, create `.vscode/settings.json`:

```json
{
  "java.import.gradle.enabled": true,
  "java.configuration.updateBuildConfiguration": "automatic",
  "java.import.gradle.wrapper.enabled": true,
  "java.import.gradle.home": ""
}
```

## Troubleshooting

### "Non-Project File" Warnings

If Java files show as "non-project files":

1. **Clean and Reload Java Workspace**:
   - Open Command Palette (`Cmd+Shift+P` / `Ctrl+Shift+P`)
   - Run: `Java: Clean Java Language Server Workspace`
   - Then: `Developer: Reload Window`

2. **Reimport Gradle Projects**:
   - Open Command Palette
   - Run: `Java: Import Java Projects in Workspace`

3. **Build First**:
   ```bash
   ./gradlew clean build
   ```

### IDE Not Finding Dependencies

If imports aren't resolving:

1. Ensure Gradle build completed successfully
2. Check that `libs/*/build/libs/*.jar` files exist
3. Reimport Gradle project

### Alternative: Open as Root Project

If issues persist, open `lesson16-nx-gradle` directly:
- File → Open Folder → Select `lesson16-nx-gradle`
- This treats it as the workspace root

## Running Commands

### Via Gradle (Direct)

```bash
# Build all
./gradlew buildAll

# Test all
./gradlew testAll

# Run apps
./gradlew :apps:cli-app:run
./gradlew :apps:service-app:run
```

### Via Nx (With Caching)

```bash
# Show all projects
npx nx show projects

# Build all
npx nx run-many --target=build --all

# Test all
npx nx run-many --target=test --all

# Run apps
npx nx run :apps:cli-app:run
npx nx run :apps:service-app:run
```

## Nx Console Integration

With the **Nx Console** extension installed:

1. Open the Nx Console panel (sidebar icon)
2. View all projects and their targets
3. Run tasks with a single click
4. Visualize the project dependency graph

## Key Files

| File | Purpose |
|------|---------|
| `settings.gradle` | Defines Gradle subprojects |
| `build.gradle` | Root build script (applies plugins to subprojects) |
| `nx.json` | Nx workspace configuration |
| `package.json` | Node.js dependencies (Nx, @nx/gradle) |
| `gradle.properties` | Gradle JVM and daemon settings |

## Notes

- The root `build.gradle` is a **container project** (no Java plugin directly)
- All Java code lives in `apps/*` and `libs/*` subprojects
- Use `pnpm` for Node.js package management (not npm)
- Nx v22.4.1+ is required for proper Gradle project discovery
