import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
    `kotlin-dsl`
}

group = "me.retty.buildlogic"

repositories {
    google()
    mavenCentral()
    gradlePluginPortal()
}

val compileKotlin: KotlinCompile by tasks
compileKotlin.compilerOptions.jvmTarget.set(JvmTarget.JVM_21)

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

dependencies {
    implementation(libs.androidGradlePlugin)
    implementation(libs.kotlinGradlePlugin)
}

gradlePlugin {
    plugins {
        register("WatchFaceFormatGenerator") {
            id = "net.matsudamper.WatchFaceFormatGenerator"
            implementationClass = "net.matsudamper.WatchFaceFormatGeneratorPlugin"
        }
    }
}
