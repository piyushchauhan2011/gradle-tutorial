// Lesson 11: Consuming Published Artifacts - Kotlin DSL

plugins {
    java
    application
}

application {
    mainClass.set("Main")
}

repositories {
    maven {
        url = uri("../remote-repo")
    }
}

dependencies {
    implementation("com.example:library:1.0")
}
