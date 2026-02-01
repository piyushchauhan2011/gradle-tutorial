// Lesson 18: JavaFX Desktop Applications - Kotlin DSL

plugins {
    java
    application
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
}

repositories {
    mavenCentral()
}

// JavaFX version
val javafxVersion = "21.0.2"
// Detect platform for natives
val os = System.getProperty("os.name").lowercase()
val arch = System.getProperty("os.arch").lowercase()
val platform = when {
    os.contains("mac") -> if (arch.contains("aarch64") || arch.contains("arm64")) "mac-aarch64" else "mac"
    os.contains("win") -> "win"
    else -> "linux"
}

dependencies {
    // JavaFX modules with platform-specific natives
    implementation("org.openjfx:javafx-controls:$javafxVersion:$platform")
    implementation("org.openjfx:javafx-fxml:$javafxVersion:$platform")
    
    // Testing
    testImplementation("junit:junit:4.13.2")
    testImplementation("org.testfx:testfx-core:4.0.18")
    testImplementation("org.testfx:testfx-junit:4.0.18")
}

application {
    mainClass.set("com.example.javafx.TodoListApp")
}

// Configure JavaFX runtime manually
tasks.named<JavaExec>("run") {
    doFirst {
        System.setProperty("java.awt.headless", "false")
    }
    // Ensure output is visible
    standardOutput = System.out
    errorOutput = System.err
    
    // Configure module path and modules
    val modulePath = configurations.runtimeClasspath.get().asPath
    val javaFxModules = "javafx.controls,javafx.fxml"
    
    if (os.contains("mac")) {
        // macOS requires -XstartOnFirstThread as FIRST argument
        jvmArgs = listOf(
            "-XstartOnFirstThread",
            "--module-path", modulePath,
            "--add-modules", javaFxModules
        )
    } else {
        jvmArgs = listOf(
            "--module-path", modulePath,
            "--add-modules", javaFxModules
        )
    }
}
