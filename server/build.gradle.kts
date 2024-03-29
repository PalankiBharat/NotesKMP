
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

    // auth jwt
    implementation(libs.ktor.server.auth)
    implementation(libs.ktor.server.jwt)

    // commons codec
    implementation(libs.commonCodec)

    implementation(libs.ktor.koin)
    implementation(libs.koin.core)
    implementation(libs.koin.logger)
}

tasks.jar {
    manifest {
        attributes["Main-Class"] = "com.bharat.noteskmp.ApplicationKt"
    }
}
ktor {
    fatJar {
        archiveFileName.set("fat.jar")
    }
}


