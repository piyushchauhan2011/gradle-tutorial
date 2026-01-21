# Lesson 4: Managing Tasks

## Description
Advanced task creation and lifecycle.

## Key Concepts
- Task types
- Inputs/outputs
- Task graph

## Runnable Examples
- Run `./gradlew -p lesson04-tasks process` (prints "Hello").
- Run `./gradlew -p lesson04-tasks copyFiles` (copies README.md to build/copy and depends on build).

## Exercises
1. Add a task that copies files and depends on build. (Completed: Added `copyFiles` task that copies *.md files to build/copy and depends on the build task.)
