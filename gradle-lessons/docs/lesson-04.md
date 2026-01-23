# Lesson 4: Managing Tasks

## Overview
Tasks can have inputs, outputs, and dependencies for incremental builds.

## Inputs/Outputs
- `inputs.property()` for properties
- `outputs.file()` for files

## Exercise Solution
Groovy DSL:
```
task copyFiles(type: Copy) {
    from '.'
    include '*.md'
    into 'build/copy'
    dependsOn build
}
```

Kotlin DSL:
```
tasks.register<Copy>("copyFiles") {
    from(".")
    include("*.md")
    into("build/copy")
    dependsOn("build")
}
```
