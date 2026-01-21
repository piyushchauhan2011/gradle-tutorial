# Lesson 3: Writing Your First Build Script

## Overview
Build scripts define tasks and configurations. Tasks are units of work with actions.

## Task Definition
- Groovy: `task myTask { doLast { ... } }`
- Kotlin: `tasks.register("myTask") { doLast { ... } }`

## Running the Example
```bash
./gradlew customTask
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

Run `./gradlew dependentTask`.
