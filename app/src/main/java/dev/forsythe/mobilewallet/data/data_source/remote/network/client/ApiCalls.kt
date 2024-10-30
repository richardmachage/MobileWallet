package dev.forsythe.mobilewallet.data.data_source.remote.network.client

import dev.forsythe.mobilewallet.data.data_source.remote.network.model.request.BalanceRequest
import dev.forsythe.mobilewallet.data.data_source.remote.network.model.request.GetAccountRequest
import dev.forsythe.mobilewallet.data.data_source.remote.network.model.request.LastHundredTransactionsRequest
import dev.forsythe.mobilewallet.data.data_source.remote.network.model.request.LogInRequest
import dev.forsythe.mobilewallet.data.data_source.remote.network.model.request.SendMoneyRequest
import dev.forsythe.mobilewallet.data.data_source.remote.network.model.response.AccountResponse
import dev.forsythe.mobilewallet.data.data_source.remote.network.model.response.BalanceResponse
import dev.forsythe.mobilewallet.data.data_source.remote.network.model.response.LogInResponse
import dev.forsythe.mobilewallet.data.data_source.remote.network.model.response.Response
import dev.forsythe.mobilewallet.data.data_source.remote.network.model.response.SendMoneyResponse
import dev.forsythe.mobilewallet.data.data_source.remote.network.model.response.TransactionResponse
import io.ktor.client.call.body
import io.ktor.client.request.post
import io.ktor.client.request.setBody
import io.ktor.client.request.url
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentRange
import io.ktor.http.ContentType
import io.ktor.http.HttpStatusCode
import io.ktor.http.contentType

suspend fun KtorClient.customerLogIn(customerId : String, pin :String): Response<*> {
    val result = this.client.post {
        url("/api/v1/customers/login")
        contentType(ContentType.Application.Json)
        setBody(LogInRequest(customerId,pin))
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

suspend fun KtorClient.accountBalance(accountNo: String, customerId: String): Response<*> {
    val result = this.client.post {
        url("/api/v1/accounts/balance")
        contentType(ContentType.Application.Json)
        setBody(BalanceRequest(accountNo,customerId))
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

suspend fun KtorClient.lastHundredTransactions(customerId: String): Response<*> {
    val result = this.client.post {
        url("/api/v1/transactions/last-100-transactions")
        contentType(ContentType.Application.Json)
        setBody(LastHundredTransactionsRequest(customerId))
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

suspend fun KtorClient.sendMoney(sendMoneyRequest: SendMoneyRequest) : Response<*> {
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

suspend fun KtorClient.getUserAccount(customerId : String) : Response<*> {
    val result = this.client.post {
        url("/api/v1/accounts/")
        contentType(ContentType.Application.Json)
        setBody(GetAccountRequest(customerId = customerId))
    }

    return if (result.status == HttpStatusCode.OK)
        Response(
            body = result.body<AccountResponse>(),
            statusCode = result.status
        )
    else
        Response(
            body = result.body<String>(),
            statusCode = result.status
        )
}