plugins {
    alias(libs.plugins.multiplatform)
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlinX.serialization.plugin)
    alias(libs.plugins.kotlinX.parcelable.plugin)
    alias(libs.plugins.compose.multiplatform)
    alias(libs.plugins.sqlDelight.plugin)
    alias(libs.plugins.moko.plugin)
}

kotlin {
    androidTarget()

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "shared"
            isStatic = true
        }
    }

    sourceSets {
        val commonMain by getting {
            dependencies {
                api(libs.koin.core)
                api(libs.koin.compose)

                implementation(libs.voyager.navigator)
                implementation(libs.voyager.bottomSheetNavigator)
                implementation(libs.voyager.transitions)
                implementation(libs.voyager.tabNavigator)

                implementation(compose.runtime)
                implementation(compose.foundation)
                implementation(compose.material3)
                implementation(compose.material)

                @OptIn(org.jetbrains.compose.ExperimentalComposeLibrary::class)
                implementation(compose.components.resources)

                implementation(libs.ktor.core)
                implementation(libs.ktor.json)
                implementation(libs.ktor.logging)
                implementation(libs.ktor.serialization)
                implementation(libs.ktor.contentNegotiation)
                implementation(libs.ktor.json)

                implementation(libs.sqlDelight.runtime)
                implementation(libs.sqlDelight.coroutine)
                implementation(libs.primitive.adapters)

                implementation(libs.kotlinX.serializationJson)

                implementation(libs.moko.resources)
                implementation(libs.moko.compose)
                implementation(libs.moko.mvvmCompose)

                implementation(libs.image.loader)

                implementation(libs.stdlib)

                implementation(libs.vico.compose)
                implementation(libs.vico.compose.m2)
                implementation(libs.vico.compose.m3)
                implementation(libs.vico.core)
                implementation(libs.vico.views)
            }
        }
        val androidMain by getting {
            dependsOn(commonMain)
            dependencies {
                implementation(libs.sqlDelight.android)
                implementation(libs.compose.activity)
                implementation(libs.core)
                implementation(libs.ktor.clientOkhttp)
                implementation(libs.tooling.preview)
                implementation(libs.ui.tooling)
            }
        }
        val iosX64Main by getting
        val iosArm64Main by getting
        val iosSimulatorArm64Main by getting
        val iosMain by creating {
            dependsOn(commonMain)
            iosX64Main.dependsOn(this)
            iosArm64Main.dependsOn(this)
            iosSimulatorArm64Main.dependsOn(this)
            dependencies {
                implementation(libs.sqlDelight.native)
                implementation(libs.ktor.clientDarwin)
            }
        }
    }
}

sqldelight {
    databases {
        create("CryptoDatabase") {
            packageName.set("com.akcay.cryptokmm.database")
        }
    }
}

android {
    compileSdk = (findProperty("android.compileSdk") as String).toInt()
    namespace = "com.akcay.cryptokmm"

    sourceSets["main"].manifest.srcFile("src/androidMain/AndroidManifest.xml")
    sourceSets["main"].res.srcDirs("src/androidMain/res")
    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }

    defaultConfig {
        minSdk = (findProperty("android.minSdk") as String).toInt()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.2"
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlin {
        jvmToolchain(17)
    }
}
