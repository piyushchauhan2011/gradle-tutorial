// Lesson 12: Kotlin Consumer App - Kotlin DSL

plugins {
    kotlin("jvm") version "1.9.20"
    application
}

application {
    mainClass.set("MainKt")
}

kotlin {
    jvmToolchain(21)
}

repositories {
    mavenCentral()
    maven {
        url = uri("../remote-repo")
    }
}

dependencies {
    implementation("com.example:library:1.0")
}
