// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    id("com.android.application") version "7.3.1" apply false
    id("com.android.library") version "7.3.1" apply false
    id("org.jetbrains.kotlin.android") version "1.7.20" apply false
    id("org.sonarqube") version "3.5.0.2730"
}

sonarqube {
  properties {
    property("sonar.projectKey", "JahidHasanCO_BMI-Calculator")
    property("sonar.organization", "jahidhasanco")
    property("sonar.host.url", "https://sonarcloud.io")
  }
}

tasks.register("clean",Delete::class){
    delete(rootProject.buildDir)
}