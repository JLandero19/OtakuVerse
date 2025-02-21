// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.kotlin.android) apply false
    alias(libs.plugins.kotlin.compose) apply false

    kotlin("jvm") version "2.1.10"
    kotlin("plugin.serialization") version "2.1.10"
    id("com.google.devtools.ksp") version "2.1.0-1.0.29" apply false
}

buildscript {
    extra.apply {
        set("room_version", "2.6.1")
    }
}