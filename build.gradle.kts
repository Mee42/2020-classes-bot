import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    java
    kotlin("jvm") version "1.4.0"
    id("com.github.johnrengelman.shadow") version "6.0.0"
}

group = "dev.mee42"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
    jcenter()
}



configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_14
    targetCompatibility = JavaVersion.VERSION_14
}

dependencies {
    implementation(kotlin("stdlib"))
    testCompile("junit", "junit", "4.12")
    implementation("com.discord4j","discord4j-core","3.1.1")
    implementation("org.jetbrains.kotlinx","kotlinx-coroutines-reactor","1.3.9")
    implementation("org.jdbi","jdbi3-core", "3.14.4")
    implementation("org.jdbi","jdbi3-sqlobject", "3.14.4")
    implementation("org.jdbi","jdbi3-kotlin", "3.14.4")
    implementation("org.jdbi","jdbi3-kotlin-sqlobject", "3.14.4")
    implementation("org.xerial","sqlite-jdbc","3.32.3.2")
    implementation("com.sedmelluq:lavaplayer:1.3.50")
//    implementation("org.slf4j","slf4j-simple","1.7.25")
}
tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}

//mainClassName = "dev.mee42.MainKt"
val compileKotlin: KotlinCompile by tasks
compileKotlin.kotlinOptions {
    freeCompilerArgs = listOf("-Xinline-classes")
}
tasks.withType<Jar> {
    manifest {
        attributes(
                mutableMapOf(
                        "Main-Class" to "dev.mee42.MainKt"
                )
        )
    }
}

