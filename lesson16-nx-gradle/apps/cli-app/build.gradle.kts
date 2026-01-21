plugins {
    java
    application
}

dependencies {
    implementation(project(":libs:core-utils"))
}

application {
    mainClass.set("com.example.apps.CliApp")
}

tasks.register("showInfo") {
    doLast {
        println("CLI App - depends on core-utils")
    }
}
