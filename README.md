# Gradle Tutorial

This is a comprehensive tutorial to learn Gradle and Kotlin step by step. The project is structured as a multi-module Gradle build with lessons as subprojects.

## Prerequisites
- Java installed (JDK 11 or later recommended)
- No need for Groovy or Kotlin CLI tools

## Setup
1. Clone or download this project.
2. Run `./g tasks` to see available tasks. (Alternatively, use `./gradlew tasks` if `./g` fails.)
3. Navigate to each lesson directory and follow the README.md.
4. Each lesson can be run using: `./g -p <lesson-name> <task>`

## Running Examples

Use the `./g` script (recommended) with the `-p` flag to specify the lesson:
```bash
./g -p lesson01-intro hello         # Run a specific lesson task
./g -p lesson02-setup build        # Build a lesson
./g -p lesson15-image-processing run  # Run an example
```

Alternative using `./gradlew`:
```bash
./gradlew -p lesson01-intro hello
./gradlew -p lesson02-setup build
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
14. [Lesson 15: Image Processing and Manipulation](lesson15-image-processing/README.md)

Each lesson includes runnable examples and exercises. See the "Running Examples" section above for command syntax.
