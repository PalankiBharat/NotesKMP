package data.api

import data.preferance.PreferenceManager
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.header
import io.ktor.serialization.kotlinx.json.json
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class HttpClient:KoinComponent{
    val preferenceManager:PreferenceManager by inject()
    val BASE_URL: String = "http://10.0.2.2:8081/v1/"

    val httpClient = HttpClient {
        expectSuccess = true
        install(HttpTimeout) {
            val timeout = 50000L
            connectTimeoutMillis = timeout
            requestTimeoutMillis = timeout
            socketTimeoutMillis = timeout
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.HEADERS
        }
        install(ContentNegotiation) {
            json()
        }
        defaultRequest{
            url(BASE_URL)
            preferenceManager.getToken().isNotBlank().
            header("Authorization","Bearer ${"sdsd"}")
        }
    }
}

