# Lesson 13: Scala Consumer App

## Overview
Consume artifacts in a Scala application.

## Scala Plugin
Apply `scala` plugin for Scala compilation.

## Application Plugin
Set `mainClass` to the Scala object (e.g., `Main`).

## Example
```
plugins {
    id 'scala'
    id 'application'
}

dependencies {
    implementation 'org.scala-lang:scala-library:2.13.12'
    implementation 'com.example:library:1.0'
}
```

From the project root, run:
```bash
./g -p lesson13-scala-consumer run
```

Alternatively:
```bash
./gradlew -p lesson13-scala-consumer run
```
