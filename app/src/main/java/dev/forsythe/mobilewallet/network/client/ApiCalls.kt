package dev.forsythe.mobilewallet.network.client

import dev.forsythe.mobilewallet.network.model.request.LogInRequest
import dev.forsythe.mobilewallet.network.model.response.LogInResponse
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.http.ContentType
import io.ktor.http.contentType

suspend fun KtorClient.customerLogIn(logInRequest: LogInRequest) : LogInResponse {
    return this.client.post{
        url("/api/v1/customers/login")
        contentType(ContentType.Application.Json)
        setBody(logInRequest)
    }.body()
}