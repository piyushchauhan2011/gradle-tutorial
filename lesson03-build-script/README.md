# Lesson 3: Writing Your First Build Script

## Description
Basics of build scripts and tasks.

## Key Concepts
- Task definition
- doLast
- Dependencies

## Runnable Examples
- Run `./g -p lesson03-build-script customTask` (prints "Custom task executed").
- Run `./g -p lesson03-build-script dependentTask` (runs customTask first, then prints "Dependent task executed after customTask").
- Alternative: `./gradlew -p lesson03-build-script customTask` or `./gradlew -p lesson03-build-script dependentTask`

## Exercises
1. Create a task that depends on another and prints output. (Completed: Added `dependentTask` that depends on `customTask` and prints a message.)
