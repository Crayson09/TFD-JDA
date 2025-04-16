
plugins {
    kotlin("jvm") version "2.0.21"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "de.crayson"
version = "1.0"

repositories {
    mavenCentral()
}



dependencies {

    implementation("org.yaml:snakeyaml:2.2")

    testImplementation(kotlin("test"))
}

tasks.jar{
    archiveBaseName.set("tfd")
    archiveVersion.set("1.0")
    archiveClassifier.set("")
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}