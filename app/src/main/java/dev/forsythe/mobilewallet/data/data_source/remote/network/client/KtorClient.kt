package dev.forsythe.mobilewallet.data.data_source.remote.network.client

import dev.forsythe.mobilewallet.data.data_source.remote.network.model.request.LogInRequest
import dev.forsythe.mobilewallet.data.data_source.remote.network.model.response.LogInResponse
import dev.forsythe.mobilewallet.utils.BASE_URL
import dev.forsythe.mobilewallet.utils.BASE_URL_EMULATOR
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.engine.okhttp.OkHttpEngine
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.plugins.logging.DEFAULT
import io.ktor.client.plugins.logging.LogLevel
import io.ktor.client.plugins.logging.Logger
import io.ktor.client.plugins.logging.Logging
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import okhttp3.OkHttpClient

class KtorClient(
    baseUrl : String = BASE_URL_EMULATOR
){
    val client = HttpClient(OkHttp){
        defaultRequest {
            url {
                protocol = URLProtocol.HTTP
                host = baseUrl
            }
        }

        install(Logging){
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }

        install(ContentNegotiation) {
            json(Json {
                ignoreUnknownKeys = true
            })
        }

    }
}

