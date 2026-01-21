# Lesson 10: Publishing Artifacts

## Publishing
Use `maven-publish` plugin to publish to repos.

## Local Repo
`publishToMavenLocal` publishes to ~/.m2/repository.

## Exercise Solution
Add `repositories { maven { url = uri('https://repo.example.com') } }`.
