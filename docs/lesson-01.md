# Lesson 1: Introduction to Gradle

## Overview
Gradle is a build automation tool that uses a Groovy or Kotlin-based DSL to define build scripts. It supports incremental builds, dependency management, and multi-project builds.

## Benefits
- Flexible and extensible
- Fast builds with incremental compilation
- Rich plugin ecosystem
- Supports multiple languages

## Comparison
- **Maven**: XML-based, convention over configuration.
- **Ant**: XML-based, imperative.
- **Gradle**: DSL-based, combines best of both.

## Running the Example
From the project root:
```bash
./g -p lesson01-intro hello
```

Alternatively:
```bash
./gradlew -p lesson01-intro hello
```

This executes the "hello" task defined in the build script.

## Exercise Solution
To print "Hello, World!", change the println to:
- Groovy: `println 'Hello, World!'`
- Kotlin: `println("Hello, World!")`
