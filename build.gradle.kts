// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    dependencies {
        classpath("org.sonarsource.scanner.gradle:sonarqube-gradle-plugin:2.7.1")
    
    }
}

plugins {
    id("com.android.application") version "7.3.1" apply false
    id("com.android.library") version "7.3.1" apply false
    id("org.jetbrains.kotlin.android") version "1.7.20" apply false

}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}