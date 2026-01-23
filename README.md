# Gradle Tutorial

This repository contains comprehensive tutorials for learning Gradle and Kotlin. The project is organized into two separate workspaces that should be opened independently in VSCode.

## 📁 Project Structure

This repository contains two main workspaces:

### 1. `gradle-lessons/` - Pure Gradle Tutorial (Lessons 1-15)
A comprehensive step-by-step tutorial covering Gradle fundamentals:
- Basic Gradle concepts and setup
- Build scripts, tasks, and dependencies
- Plugins and multi-module projects
- Testing, custom tasks, and publishing
- Kotlin, Scala, and Swing GUI examples

**To use:** Open the `gradle-lessons/` folder in VSCode as a separate workspace.

### 2. `lesson16-nx-gradle/` - Nx + Gradle Multi-Module Monorepo (Lesson 16)
Advanced monorepo management using Nx with Gradle and pnpm workspaces:
- Multi-module Gradle project (apps and libs as direct subprojects)
- Nx workspace configuration
- pnpm workspaces for package management
- Build caching and dependency management
- Nx CLI integration with Gradle

**To use:** Open the `lesson16-nx-gradle/` folder in VSCode as a separate workspace.

## 🚀 Quick Start

### For Gradle Lessons (gradle-lessons/)
```bash
cd gradle-lessons
./gradlew tasks
./gradlew -p lesson01-intro hello
```

### For Nx + Gradle Monorepo (lesson16-nx-gradle/)
```bash
cd lesson16-nx-gradle
pnpm install
pnpm run build
```

## 📚 Getting Started

1. **For Gradle fundamentals:** Open `gradle-lessons/` folder in VSCode
2. **For Nx + Gradle monorepo:** Open `lesson16-nx-gradle/` folder in VSCode

Each workspace has its own README with detailed instructions.

## Prerequisites

- **Java:** JDK 11 or later
- **Node.js & pnpm:** Required only for `lesson16-nx-gradle/` (Node.js 16+, pnpm 8+)

---

**Note:** Each workspace should be opened separately in VSCode for the best experience with proper IDE support and configuration.
