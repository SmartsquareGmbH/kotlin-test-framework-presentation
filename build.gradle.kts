import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    kotlin("jvm") version "1.3.60"
}

repositories {
    jcenter()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.3.60")

    testImplementation("org.junit.jupiter:junit-jupiter:5.5.2")
    testImplementation("org.spekframework.spek2:spek-dsl-jvm:2.0.8")

    testRuntimeOnly("org.spekframework.spek2:spek-runner-junit5:2.0.8")
    testRuntimeOnly("org.jetbrains.kotlin:kotlin-reflect:1.3.60")
}

tasks.test {
    useJUnitPlatform {
        includeEngines("spek2")
    }
}

tasks.withType<KotlinCompile>().configureEach {
    kotlinOptions.jvmTarget = "1.8"
}
