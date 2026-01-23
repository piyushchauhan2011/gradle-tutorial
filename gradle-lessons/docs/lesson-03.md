# Lesson 3: Writing Your First Build Script

## Overview
Build scripts define tasks and configurations. Tasks are units of work with actions.

## Task Definition
- Groovy: `task myTask { doLast { ... } }`
- Kotlin: `tasks.register("myTask") { doLast { ... } }`

## Running the Example
From the project root:
```bash
./gradlew -p lesson03-build-script customTask
```

## Exercise Solution
Groovy DSL:
```
task dependentTask {
    dependsOn customTask
    doLast {
        println 'Dependent task executed after customTask'
    }
}
```

Kotlin DSL:
```
tasks.register("dependentTask") {
    dependsOn("customTask")
    doLast {
        println("Dependent task executed after customTask")
    }
}
```

Run `./gradlew -p lesson03-build-script dependentTask` from the project root.
