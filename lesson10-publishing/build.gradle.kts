// Lesson 10: Publishing Artifacts - Kotlin DSL

plugins {
    `java-library`
    `maven-publish`
}

group = "com.example"
version = "1.0"

publishing {
    publications {
        create<MavenPublication>("maven") {
            from(components["java"])
        }
    }
}
