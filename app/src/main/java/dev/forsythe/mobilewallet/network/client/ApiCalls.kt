package dev.forsythe.mobilewallet.network.client

import dev.forsythe.mobilewallet.network.model.request.BalanceRequest
import dev.forsythe.mobilewallet.network.model.request.LastHundredTransactionsRequest
import dev.forsythe.mobilewallet.network.model.request.LogInRequest
import dev.forsythe.mobilewallet.network.model.request.SendMoneyRequest
import dev.forsythe.mobilewallet.network.model.response.BalanceResponse
import dev.forsythe.mobilewallet.network.model.response.LogInResponse
import dev.forsythe.mobilewallet.network.model.response.Response
import dev.forsythe.mobilewallet.network.model.response.SendMoneyResponse
import dev.forsythe.mobilewallet.network.model.response.TransactionResponse
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType

suspend fun KtorClient.customerLogIn(logInRequest: LogInRequest): Response<*> {
    val result = this.client.post {
        url("/api/v1/customers/login")
        contentType(ContentType.Application.Json)
        setBody(logInRequest)
    }

    return if (result.status == HttpStatusCode.OK)
        Response(
            body = result.body<LogInResponse>(),
            statusCode = result.status
        )
    else
        Response(
            body = result.body<String>(),
            statusCode = result.status
        )
}

suspend fun KtorClient.accountBalance(balanceRequest: BalanceRequest): Response<*> {
    val result = this.client.post {
        url("/api/v1/accounts/balance")
        contentType(ContentType.Application.Json)
        setBody(balanceRequest)
    }

    return if (result.status == HttpStatusCode.OK)
        Response(
            body = result.body<BalanceResponse>(),
            statusCode = result.status
        )
    else
        Response(
            body = result.body<String>(),
            statusCode = result.status
        )
}

suspend fun KtorClient.lastHundredTransactions(lastHundredTransactionsRequest: LastHundredTransactionsRequest): Response<*> {
    val result = this.client.post {
        url("/api/v1/transactions/last-100-transactions")
        contentType(ContentType.Application.Json)
        setBody(lastHundredTransactionsRequest)
    }

    return if (result.status == HttpStatusCode.OK)
        Response(
            body = result.body<List<TransactionResponse>>(),
            statusCode = result.status
        )
    else
        Response(
            body = result.body<String>(),
            statusCode = result.status
        )
}

suspend fun KtorClient.sendMoney(sendMoneyRequest: SendMoneyRequest) : Response<*>{
    val result = this.client.post {
        url("/api/v1/transactions/send-money")
        contentType(ContentType.Application.Json)
        setBody(sendMoneyRequest)
    }

    return if (result.status == HttpStatusCode.OK)
        Response(
            body = result.body<SendMoneyResponse>(),
            statusCode = result.status
        )
    else
        Response(
            body = result.body<String>(),
            statusCode = result.status
        )
}