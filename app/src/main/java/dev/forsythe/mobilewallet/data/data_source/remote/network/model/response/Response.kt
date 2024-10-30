package dev.forsythe.mobilewallet.data.data_source.remote.network.model.response

import io.ktor.http.HttpStatusCode
import kotlinx.serialization.Serializable


data class Response <T>(
    val body: T,
    val statusCode: HttpStatusCode
)
