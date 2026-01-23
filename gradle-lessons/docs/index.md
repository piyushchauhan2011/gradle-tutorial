# Gradle Tutorial Documentation

## Overview
This tutorial guides you through learning Gradle, a powerful build automation tool, and Kotlin, a modern programming language. Each lesson builds on the previous one, covering both Groovy DSL and Kotlin DSL for Gradle build scripts.

## Structure
- **Lessons**: Step-by-step guides with theory, examples, and exercises.
- **DSLs**: Both Groovy (.gradle) and Kotlin (.gradle.kts) versions provided.
- **Runnable Examples**: Execute with `./gradlew -p <lesson-name> <task>` from the root.
- **Exercises**: Hands-on tasks to practice concepts.

## Getting Started
1. Ensure Java is installed.
2. Run `./gradlew tasks` from the root.
3. Start with Lesson 1 and progress sequentially.

## Running Examples
All examples run from the project root using:
```bash
./gradlew -p <lesson-name> <task>
```

For example:
- `./gradlew -p lesson01-intro hello`
- `./gradlew -p lesson05-dependencies test`
- `./gradlew -p lesson15-image-processing run`

## Resources
- [Gradle User Manual](https://docs.gradle.org/current/userguide/)
- [Kotlin Documentation](https://kotlinlang.org/docs/)
