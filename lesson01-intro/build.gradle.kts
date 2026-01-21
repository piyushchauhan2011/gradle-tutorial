// Lesson 1: Introduction to Gradle - Kotlin DSL
// This build script demonstrates a simple "hello" task.

tasks.register("hello") {
    doLast {
        println("Hello, Gradle!")
    }
}
