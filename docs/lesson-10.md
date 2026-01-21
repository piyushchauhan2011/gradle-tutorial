# Lesson 10: Publishing Artifacts

## Publishing
Use `maven-publish` plugin to publish to repos.

## Local Repo
`publishToMavenLocal` publishes to ~/.m2/repository.

## Remote Repo
Configure repositories in the `publishing` block.

## Exercise Solution
Groovy DSL:
```
repositories {
    maven {
        url = uri('../remote-repo')
    }
}
```

Kotlin DSL:
```
repositories {
    maven {
        url = uri("../remote-repo")
    }
}
```

Run `./gradlew publish` to publish to the local remote repo.
