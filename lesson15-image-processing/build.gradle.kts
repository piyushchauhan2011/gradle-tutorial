// Lesson 15: Image Processing in Java - Kotlin DSL

plugins {
    java
    application
}

repositories {
    mavenCentral()
}

dependencies {
    // Testing
    testImplementation("junit:junit:4.13.2")
}

application {
    mainClass.set("ImageProcessingUI")
}

tasks.named<JavaExec>("run") {
    doFirst {
        System.setProperty("java.awt.headless", "false")
    }
}
