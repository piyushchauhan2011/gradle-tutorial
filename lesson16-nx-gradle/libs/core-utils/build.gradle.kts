plugins {
    java
    `java-library`
}

// No external dependencies - core utilities only

tasks.register("showInfo") {
    doLast {
        println("Core Utilities Library - shared utilities for the workspace")
    }
}
