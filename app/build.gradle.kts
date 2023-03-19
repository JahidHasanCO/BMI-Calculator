plugins {
    id("com.android.application")
    id("org.jetbrains.kotlin.android")
}

android {

    compileSdk = 33

    defaultConfig {
        applicationId = "dev.jahidhasanco.bmicalculator"
        minSdk = 21
        targetSdk = 33
        versionCode = 4
        versionName = "1.0.3"

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = true
            isShrinkResources = true
            isDebuggable = false

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro"
            )
        }
    }
    buildTypes.all { isCrunchPngs = false }

    compileOptions {
        sourceCompatibility(JavaVersion.VERSION_1_8)
        targetCompatibility(JavaVersion.VERSION_1_8)
    }
    kotlinOptions {
        jvmTarget = "1.8"
    }
    android.buildFeatures.dataBinding=true

}

dependencies {

    implementation("androidx.core:core-ktx:1.9.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("com.google.android.material:material:1.8.0")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.5")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.5.1")

    implementation("com.github.adityagohad:HorizontalPicker:1.0.1")
    implementation("com.github.psuzn:WheelView:1.0.0")
    implementation("com.github.CNCoderX:WheelView:1.2.6")
    implementation("com.github.mhdmoh:swipe-button:1.0.3")

}
