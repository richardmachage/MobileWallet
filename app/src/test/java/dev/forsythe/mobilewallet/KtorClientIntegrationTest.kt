package dev.forsythe.mobilewallet

import dev.forsythe.mobilewallet.network.client.KtorClient
import dev.forsythe.mobilewallet.network.client.customerLogIn
import dev.forsythe.mobilewallet.network.model.request.LogInRequest
import dev.forsythe.mobilewallet.network.model.response.LogInResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.engine.mock.MockEngine
import io.ktor.client.engine.mock.respond
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.utils.EmptyContent.contentType
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType
import io.ktor.http.headersOf
import io.ktor.serialization.kotlinx.json.json
import junit.framework.TestCase.assertEquals
import junit.framework.TestCase.assertTrue
import kotlinx.coroutines.runBlocking
import kotlinx.serialization.json.Json
import org.junit.Test

class KtorClientIntegrationTest {
    private val client = KtorClient()
    @Test
    fun `test successful login` (): Unit = runBlocking {
        val result = client.customerLogIn(
            logInRequest = LogInRequest(
                customerId = "CUST1001",
                pin = "1234"
            )
        )

        assertTrue(result.customerId.isNotEmpty())
    }
}

val mockResponse = """
{
    "firstName": "John1001",
    "lastName": "Doe1001",
    "customerId": "CUST1001",
    "email": "johndoe1001@gmail.com"
}
"""

class KtorClientTest {

    private val client = HttpClient(MockEngine) {
        engine {
            addHandler { request ->
                when (request.url.encodedPath) {
                    "/api/v1/customers/login" -> {
                        respond(
                            content = mockResponse,
                            status = HttpStatusCode.OK,
                            headers = headersOf("Content-Type" to listOf(ContentType.Application.Json.toString()))
                        )
                    }
                    else -> error("Unhandled ${request.url.encodedPath}")
                }
            }
        }

        install(ContentNegotiation) {
            json(Json { ignoreUnknownKeys = true })
        }
    }

    @Test
    fun `test successful login`() = runBlocking {
        val result = client.post {
            url("/api/v1/customers/login")
            contentType(ContentType.Application.Json)
            setBody(LogInRequest(customerId = "CUST1001", pin = "1234"))
        }.body<LogInResponse>()

        assertTrue(result.customerId.isNotEmpty())
    }
}