package com.example.core.http_clients

import io.ktor.client.*
import io.ktor.client.engine.android.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class KtorClient {

    companion object {
        val ktorClient = HttpClient(Android) {
            defaultRequest {
                url("https://jsonplaceholder.typicode.com")
            }

            install(ContentNegotiation) {
                json(Json{
                    followRedirects = false
                    expectSuccess = false
                    ignoreUnknownKeys = true
                })
            }

            install(Logging) {
                logger = Logger.ANDROID
                level = LogLevel.ALL
            }
        }
    }

}