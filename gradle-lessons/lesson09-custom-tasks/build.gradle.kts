// Lesson 9: Custom Tasks and Plugins - Kotlin DSL

open class GreetingTask : DefaultTask() {
    @TaskAction
    fun greet() {
        println("Hello from custom task!")
    }
}

tasks.register<GreetingTask>("custom")
