# Lesson 3: Writing Your First Build Script

## Description
Basics of build scripts and tasks.

## Key Concepts
- Task definition
- doLast
- Dependencies

## Runnable Examples
- Groovy DSL: Run `./gradlew customTask` (prints "Custom task executed").
- Kotlin DSL: Same command.
- Run `./gradlew dependentTask` (runs customTask first, then prints "Dependent task executed after customTask").

## Exercises
1. Create a task that depends on another and prints output. (Completed: Added `dependentTask` that depends on `customTask` and prints a message.)
