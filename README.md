# Gradle Tutorial

This is a comprehensive tutorial to learn Gradle and Kotlin step by step. The project is structured as a multi-module Gradle build with lessons as subprojects. Advanced lessons include Nx for monorepo management.

## Prerequisites
- Java installed (JDK 11 or later recommended)
- Node.js 16+ and npm 8+ (optional, for Nx integration in Lesson 16)

## Setup

### 1. Quick Start (Gradle Only)
```bash
# Just Gradle, no Nx required
./g tasks
./g -p lesson01-intro hello
```

### 2. Full Setup (Gradle + Nx)
```bash
# Install Nx and workspace dependencies
npm install

# Now you can use both Gradle and Nx commands
./g -p lesson16-nx-gradle buildAll  # Gradle commands
npm run build                         # Nx commands
```

## Running Examples

### Using Gradle CLI (Works immediately)
Use the `./g` script (recommended) with the `-p` flag to specify the lesson:
```bash
./g -p lesson01-intro hello              # Run a specific lesson task
./g -p lesson02-setup build              # Build a lesson
./g -p lesson15-image-processing run     # Run an example
./g -p lesson16-nx-gradle buildAll       # Build Nx monorepo with Gradle
```

### Using Nx CLI (After `npm install`)
```bash
npm run build                    # Build all lessons
npm run test                     # Test all lessons
npm run dep-graph               # View dependency graph
nx build apps-service-app       # Build specific Nx project
```

## Lessons
1. [Lesson 1: Introduction to Gradle](lesson01-intro/README.md)
2. [Lesson 2: Setting Up a Gradle Project](lesson02-setup/README.md)
3. [Lesson 3: Writing Your First Build Script](lesson03-build-script/README.md)
4. [Lesson 4: Managing Tasks](lesson04-tasks/README.md)
5. [Lesson 5: Handling Dependencies](lesson05-dependencies/README.md)
6. [Lesson 6: Using Plugins](lesson06-plugins/README.md)
7. [Lesson 7: Multi-Module Projects](lesson07-multi-module/README.md)
8. [Lesson 8: Testing in Gradle](lesson08-testing/README.md)
9. [Lesson 9: Custom Tasks and Plugins](lesson09-custom-tasks/README.md)
10. [Lesson 10: Publishing Artifacts](lesson10-publishing/README.md)
11. [Lesson 11: Consuming Published Artifacts](lesson11-consuming-artifacts/README.md)
12. [Lesson 12: Kotlin Consumer App](lesson12-kotlin-consumer/README.md)
13. [Lesson 13: Scala Consumer App](lesson13-scala-consumer/README.md)
14. [Lesson 14: Swing GUI Applications](lesson14-swing-gui/README.md)
15. [Lesson 15: Image Processing and Manipulation](lesson15-image-processing/README.md)
16. [Lesson 16: Nx with Gradle](lesson16-nx-gradle/README.md) - Monorepo organization, dependency management, and build caching

Each lesson includes runnable examples and exercises. See the "Running Examples" section above for command syntax.
