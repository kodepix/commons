<img align="left" alt="logo" width="128" src=".idea/icon.svg">

# &nbsp;&nbsp;&nbsp;Commons

###### &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;Useful functions and tools.

[![Kotlin](https://img.shields.io/badge/kotlin-2.1.20-blue.svg?logo=kotlin)](http://kotlinlang.org)
[![Maven Central](https://img.shields.io/maven-central/v/io.github.kodepix/commons)](https://central.sonatype.com/artifact/io.github.kodepix/commons)
![GitHub](https://img.shields.io/github/license/kodepix/commons)
[![ktlint](https://img.shields.io/badge/code%20style-%E2%9D%A4-FF4081.svg)](https://ktlint.github.io/)

---
<br>

* [Installation](#installation)
* [Development](#development)
    * [Getting started](#getting-started)
    * [Gradle wrapper generation](#gradle-wrapper-generation)
    * [Versions update](#versions-update)

## Installation

```kotlin
repositories {
    mavenCentral()
}

dependencies {
    implementation("io.github.kodepix:commons:2.0")
}
```

## Development

### Getting started

- JDK 21

### Gradle wrapper generation

```shell
./gradlew wrapper --gradle-version 8.13 --distribution-type bin
```

### Versions update

```shell
./gradlew versionCatalogUpdate
```

---
