plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.serialization)
    alias(libs.plugins.detekt)
}

kotlin {
    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = "17"
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "ComposeApp"
            isStatic = true
        }
    }

    dependencies {
        detektPlugins(libs.detekt.formatting)
    }

    sourceSets {
        all {
            languageSettings {
                optIn("org.jetbrains.compose.resources.ExperimentalResourceApi")
            }
        }


        androidMain.dependencies {
            implementation(libs.compose.ui.tooling.preview)
            implementation(libs.androidx.activity.compose)

            // ktor engine
            implementation(libs.ktor.client.cio)
        }



        commonMain.dependencies {
            api(libs.koin.core)
            api(libs.koin.compose)

            implementation(compose.runtime)
            implementation(compose.foundation)
            implementation(compose.material)
            implementation(compose.material3)
            implementation(compose.ui)
            implementation(compose.components.resources)
            implementation(compose.components.uiToolingPreview)

            implementation(libs.voyager.navigator)
            implementation(libs.voyager.bottomSheetNavigator)
            implementation(libs.voyager.transitions)
            implementation(libs.voyager.tabNavigator)
            implementation(libs.voyager.koin)
            implementation(libs.stately.common) // for voyager koin error when build ios

            api(libs.napier)

            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.content.negotiation)
            implementation(libs.ktor.client.serialization)
            implementation(libs.ktor.client.logging)

            api(libs.multiplatformSettings.noArg)
            api(libs.multiplatformSettings.coroutines)

            implementation(libs.kotlinx.datetime)
        }

        iosMain.dependencies {
            // ktor engine
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "com.anbui.skribbl"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/resources")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.anbui.skribbl"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    dependencies {
        implementation(libs.koin.android)
        debugImplementation(libs.compose.ui.tooling)
    }
}


tasks.named("assemble").configure {
    dependsOn("detekt")
}


detekt {
    source.setFrom(
        "src/androidMain/kotlin",
        "src/commonMain/kotlin"
    )
    toolVersion = "1.23.3"
    config.setFrom("../config/detekt/detekt.yml")

    buildUponDefaultConfig = true

    ignoreFailures = false
    parallel = true
    autoCorrect = true
}


