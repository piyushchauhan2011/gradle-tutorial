// Lesson 3: Writing Your First Build Script - Kotlin DSL
// Basics of build scripts and tasks.

tasks.register("customTask") {
    doLast {
        println("Custom task executed")
    }
}

tasks.register("anotherTask") {
    doLast {
        println("Another task executed")
    }
}

tasks.register("dependentTask") {
    dependsOn("customTask")
    doLast {
        println("Dependent task executed after customTask")
    }
}
