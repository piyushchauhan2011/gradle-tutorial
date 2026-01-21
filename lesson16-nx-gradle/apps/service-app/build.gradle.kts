plugins {
    java
    application
}

dependencies {
    implementation(project(":libs:core-utils"))
    implementation(project(":libs:data-models"))
}

application {
    mainClass.set("ServiceApp")
}

tasks.register("showInfo") {
    doLast {
        println("Service App - depends on core-utils and data-models")
    }
}
