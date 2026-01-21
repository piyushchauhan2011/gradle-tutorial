// Lesson 13: Scala Consumer App - Kotlin DSL

plugins {
    scala
    application
}

application {
    mainClass.set("Main")
}

repositories {
    mavenCentral()
    maven {
        url = uri("../remote-repo")
    }
}

dependencies {
    implementation("org.scala-lang:scala-library:2.13.12")
    implementation("com.example:library:1.0")
}
