# VS Code Setup for Nested Gradle Projects

## Problem

`lesson16-nx-gradle` is a nested Gradle project (it has its own `settings.gradle` and `build.gradle` files) within the `gradle-tutorial` monorepo. VS Code's Java extension may not automatically recognize nested Gradle projects, causing files like `CliApp.java` to be treated as "non-project files."

## Solution

VS Code's Java extension needs to be configured to recognize nested Gradle projects. The following configuration has been applied:

### Root Workspace Configuration (`.vscode/settings.json`)

- **Gradle import enabled**: `java.import.gradle.enabled: true`
- **Automatic build configuration**: `java.configuration.updateBuildConfiguration: "automatic"`

### Nested Project Configuration (`lesson16-nx-gradle/.vscode/settings.json`)

- Minimal configuration to ensure Gradle import works for the nested project

## How It Works

1. **Separate Gradle Project**: `lesson16-nx-gradle` maintains its own `settings.gradle` defining its subprojects (`apps:cli-app`, `apps:service-app`, `libs:core-utils`, `libs:data-models`)
2. **VS Code Recognition**: VS Code's Java extension scans for `settings.gradle` files and imports them as separate Gradle projects
3. **Gradle Commands**: Use `./g -p lesson16-nx-gradle` from the root directory

## If Issues Persist

If VS Code still doesn't recognize the nested project:

1. **Reload Java Projects**:
   - Command Palette (`Cmd+Shift+P` / `Ctrl+Shift+P`)
   - Run: "Java: Clean Java Language Server Workspace"
   - Then: "Java: Reload Projects"

2. **Alternative: Open Nested Folder Separately**:
   - File → Open Folder → Select `lesson16-nx-gradle`
   - This treats it as the root Gradle project (works but requires separate window)

3. **Check Output Panel**:
   - View → Output → Select "Language Support for Java"
   - Look for Gradle import messages or errors

## Key Files

- `.vscode/settings.json` - Root workspace Java/Gradle configuration
- `lesson16-nx-gradle/.vscode/settings.json` - Nested project configuration
- `lesson16-nx-gradle/settings.gradle` - Defines the nested Gradle project structure
- `lesson16-nx-gradle/build.gradle` - Root build script (container project, no Java plugin)

## Notes

- The root `build.gradle` does NOT have the `java` plugin (it's a container)
- All Java code is in subprojects (`apps/*` and `libs/*`)
