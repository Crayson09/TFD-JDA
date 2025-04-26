
plugins {
    kotlin("jvm") version "2.0.21"
    id("com.github.johnrengelman.shadow") version "8.1.1"
    `maven-publish`
}

group = "de.crayson"
version = "v1.0.0"

repositories {
    mavenCentral()
}



dependencies {

    implementation("org.yaml:snakeyaml:2.2")

    implementation("net.dv8tion:JDA:5.3.2")

    testImplementation(kotlin("test"))
}

tasks.jar{
    archiveBaseName.set("tfd")
    archiveVersion.set(version.toString())
    archiveClassifier.set("")
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])

            groupId = project.group.toString()
            artifactId = "tfd"
            version = project.version.toString()
        }
    }
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}