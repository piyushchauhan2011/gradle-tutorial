// Lesson 18: JavaFX Desktop Applications - Kotlin DSL

plugins {
    java
    application
    id("org.openjfx.javafxplugin") version "0.1.0"
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(21))
    }
}

repositories {
    mavenCentral()
}

javafx {
    version = "21.0.4"
    modules = listOf("javafx.controls", "javafx.fxml")
}

dependencies {
    // Testing
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.testfx:testfx-core:4.0.18")
    testImplementation("org.testfx:testfx-junit:4.0.18")
}

application {
    mainClass.set("com.example.javafx.HelloWorldApp")
}
