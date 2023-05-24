plugins {
    kotlin("jvm") version "1.8.20"
    application
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.jetbrains.kotlin:kotlin-test:1.8.20-RC")
    implementation("io.vertx:vertx-core:4.4.2")
    implementation("io.vertx:vertx-web:4.4.2")
    implementation("io.vertx:vertx-core:4.4.2")
    implementation("io.vertx:vertx-web:4.4.2")
    implementation("io.vertx:vertx-lang-kotlin:4.4.2")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin:2.12.5")
//    implementation("io.vertx:vertx-web-api-contract:4.4.2")
//    implementation("io.vertx:vertx-web-api-contract-kotlin:4.4.2")

    // Add any additional Vert.x modules or dependencies you need
}

tasks.test {
    useJUnitPlatform()
}

kotlin {
    jvmToolchain(8)
}

application {
    mainClass.set("MainKt")
}