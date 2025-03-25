import org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi
import org.jetbrains.kotlin.gradle.dsl.JvmTarget
import org.jetbrains.kotlin.gradle.plugin.mpp.KotlinNativeTarget

plugins {
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
}

kotlin {
    androidTarget {
        @OptIn(ExperimentalKotlinGradlePluginApi::class)
        compilerOptions {
            jvmTarget.set(JvmTarget.JVM_11)
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach { iosTarget ->
        iosTarget.binaries.framework {
            baseName = "Shared"
            isStatic = true
        }
    }

    sourceSets {
        commonMain.dependencies {
            // poner tus dependencias Multiplatform aquí
        }
    }
}
val packForXcode by tasks.creating(Sync::class) {
    group = "build"
    // Se puede pasar el modo (DEBUG o RELEASE) y el sdkName (iphonesimulator o iphoneos) desde las variables de entorno
    val mode = project.findProperty("mode")?.toString() ?: "DEBUG"
    val sdkName = project.findProperty("sdkName")?.toString() ?: "iphonesimulator"
    val targetName = if (sdkName.startsWith("iphoneos")) "iosArm64" else "iosX64"
    val framework = kotlin.targets.getByName<KotlinNativeTarget>(targetName).binaries.getFramework(mode)

    inputs.property("mode", mode)
    dependsOn(framework.linkTask)
    from(framework.outputDirectory)
    into(File(buildDir, "xcode-frameworks"))
}
android {
    namespace = "org.momoven.project.shared"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_11
        targetCompatibility = JavaVersion.VERSION_11
    }
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
}