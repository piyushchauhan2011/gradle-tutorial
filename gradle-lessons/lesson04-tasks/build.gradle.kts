// Lesson 4: Managing Tasks - Kotlin DSL
// Advanced task creation and lifecycle.

import org.gradle.kotlin.dsl.*

plugins {
    base
}

tasks.register("process") {
    inputs.property("message", "Hello")
    doLast {
        println(inputs.properties["message"])
    }
}

tasks.register<Copy>("copyFiles") {
    from(".")
    include("*.md")
    into("build/copy")
    dependsOn("build")
}
