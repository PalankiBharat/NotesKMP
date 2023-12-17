plugins {
    alias(libs.plugins.kotlinJvm)
    alias(libs.plugins.ktor)
    application
    alias(libs.plugins.kotlinxSerialization)

}

group = "com.bharat.noteskmp"
version = "1.0.0"
application {
    mainClass.set("com.bharat.noteskmp.ApplicationKt")
    applicationDefaultJvmArgs = listOf("-Dio.ktor.development=${extra["development"] ?: "false"}")
}

dependencies {
    implementation(projects.shared)
    implementation(libs.logback)
    implementation(libs.ktor.server.core)
    implementation(libs.ktor.server.netty)
    implementation(libs.ktor.kotlinx.serialization)
    implementation(libs.ktor.contentnegotiaiton)
    testImplementation(libs.ktor.server.tests)
    testImplementation(libs.kotlin.test.junit)

    // Mongo Driver
    implementation(libs.ktor.mongo)
    implementation(libs.mongoBson)
}
