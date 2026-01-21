// Lesson 14: Java Swing GUI - Kotlin DSL

plugins {
    java
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

repositories {
    mavenCentral()
}

dependencies {
    // No additional dependencies needed for basic Swing (included in JDK)
}

tasks.register("runSwingApp") {
    group = "application"
    description = "Runs the Swing GUI application"
    doLast {
        javaexec {
            mainClass.set("SwingApp")
            classpath = sourceSets.main.get().runtimeClasspath
        }
    }
}
