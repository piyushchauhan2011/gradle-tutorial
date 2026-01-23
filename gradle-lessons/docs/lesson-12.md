# Lesson 12: Kotlin Consumer App

## Overview
Consume artifacts in a Kotlin JVM application.

## Kotlin Plugin
Apply `kotlin("jvm")` plugin for Kotlin compilation.

## Application Plugin
Set `mainClass` to the generated class (e.g., `MainKt` for `Main.kt`).

## Example
```
plugins {
    kotlin("jvm") version "1.9.10"
    application
}

dependencies {
    implementation("com.example:library:1.0")
}
```

From the project root, run:
```bash
./gradlew -p lesson12-kotlin-consumer run
```
