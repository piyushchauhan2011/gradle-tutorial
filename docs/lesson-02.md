# Lesson 2: Setting Up a Gradle Project

## Overview
A Gradle project requires a `build.gradle` or `build.gradle.kts` file and optionally `settings.gradle`. The wrapper allows running Gradle without installation.

## Project Structure
- `src/main/java/` - Source code
- `src/test/java/` - Tests
- `build.gradle` - Build script

## Running the Example
From the lesson02-setup directory:
```bash
./gradlew build
```

This compiles the Java code and creates JARs.

## Exercise Solution
Create `Greeting.java`:
```java
public class Greeting {
    public static void main(String[] args) {
        System.out.println("Greetings!");
    }
}
```
Then run `./gradlew build`.
