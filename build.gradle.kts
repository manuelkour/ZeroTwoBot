import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    id("org.jetbrains.kotlin.jvm") version "1.4.32"
    application
}

repositories {
    mavenCentral()
    jcenter()
}

dependencies {
    implementation("dev.kord:kord-core:0.7.0-RC3")
    implementation ("khttp:khttp:1.0.0")
}

application {
    mainClass.set("BotKt")
}

tasks.withType<Jar> {
    archiveFileName.set("ztb.jar")
    manifest {
        attributes["Main-Class"] = "BotKt"
    }

    from(sourceSets.main.get().output)

    dependsOn(configurations.runtimeClasspath)
    from({
        configurations.runtimeClasspath.get().filter { it.name.endsWith("jar") }.map { zipTree(it) }
    })
}

tasks.withType<KotlinCompile>() {
    kotlinOptions.jvmTarget = "1.8"
}