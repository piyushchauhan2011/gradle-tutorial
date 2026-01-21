# Lesson 11: Consuming Published Artifacts

## Overview
Consume artifacts from local or remote repositories.

## Repository Setup
Add repositories in the `repositories` block.

## Dependencies
Declare dependencies in the `dependencies` block.

## Example
```
repositories {
    maven {
        url = uri('../remote-repo')
    }
}

dependencies {
    implementation 'com.example:library:1.0'
}
```

Run `./gradlew run` after publishing the library.
