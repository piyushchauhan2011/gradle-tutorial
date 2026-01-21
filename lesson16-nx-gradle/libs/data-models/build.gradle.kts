plugins {
    java
    `java-library`
}

// No external dependencies - data models only

tasks.register("showInfo") {
    doLast {
        println("Data Models Library - shared data structures for the workspace")
    }
}
