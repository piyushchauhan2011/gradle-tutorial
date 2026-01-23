# Gradle Tutorial - Lessons 1-15

This workspace contains comprehensive Gradle tutorials covering fundamentals and advanced topics.

## Prerequisites
- Java installed (JDK 11 or later recommended)

## Setup

### Quick Start
```bash
# List all available tasks
./gradlew tasks

# Run a specific lesson task
./gradlew -p lesson01-intro hello
./gradlew -p lesson02-setup build
./gradlew -p lesson15-image-processing run
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

Each lesson includes runnable examples and exercises. See individual lesson README files for detailed instructions.

## Running Examples

Use the `./gradlew` script with the `-p` flag to specify the lesson:

```bash
./gradlew -p lesson01-intro hello              # Run a specific lesson task
./gradlew -p lesson02-setup build              # Build a lesson
./gradlew -p lesson15-image-processing run     # Run an example
```

## Important: Lesson Dependencies

**Lessons 11, 12, and 13 depend on Lesson 10's published artifact.**

Before running lessons 11, 12, or 13, you must first publish lesson 10:

```bash
# Publish lesson 10 first (creates the artifact in remote-repo/)
./gradlew -p lesson10-publishing publish

# Now you can run the consuming lessons
./gradlew -p lesson11-consuming-artifacts run
./gradlew -p lesson12-kotlin-consumer run
./gradlew -p lesson13-scala-consumer run
```

## Documentation

Additional documentation is available in the `docs/` folder.
