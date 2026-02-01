// Lesson 16: OpenGL with Java using LWJGL - Kotlin DSL

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

// LWJGL version
val lwjglVersion = "3.3.3"
// Detect platform automatically
val os = System.getProperty("os.name").lowercase()
val arch = System.getProperty("os.arch").lowercase()
val lwjglNatives = when {
    os.contains("mac") || os.contains("darwin") -> {
        if (arch.contains("aarch64") || arch.contains("arm64")) "natives-macos-arm64" else "natives-macos"
    }
    os.contains("win") -> "natives-windows"
    else -> "natives-linux"
}

dependencies {
    // LWJGL Core
    implementation(platform("org.lwjgl:lwjgl-bom:$lwjglVersion"))
    
    // LWJGL modules
    implementation("org.lwjgl:lwjgl")
    implementation("org.lwjgl:lwjgl-assimp")
    implementation("org.lwjgl:lwjgl-glfw")
    implementation("org.lwjgl:lwjgl-openal")
    implementation("org.lwjgl:lwjgl-opengl")
    implementation("org.lwjgl:lwjgl-stb")
    
    // Native libraries (platform-specific)
    runtimeOnly("org.lwjgl:lwjgl::$lwjglNatives")
    runtimeOnly("org.lwjgl:lwjgl-assimp::$lwjglNatives")
    runtimeOnly("org.lwjgl:lwjgl-glfw::$lwjglNatives")
    runtimeOnly("org.lwjgl:lwjgl-openal::$lwjglNatives")
    runtimeOnly("org.lwjgl:lwjgl-opengl::$lwjglNatives")
    runtimeOnly("org.lwjgl:lwjgl-stb::$lwjglNatives")
    
    // Testing
    testImplementation("junit:junit:4.13.2")
}

application {
    mainClass.set("OpenGLTriangle")
}

tasks.named<JavaExec>("run") {
    doFirst {
        System.setProperty("java.awt.headless", "false")
    }
    // macOS requires main thread to be first thread for GLFW
    if (System.getProperty("os.name").lowercase().contains("mac")) {
        jvmArgs("-XstartOnFirstThread")
    }
}
