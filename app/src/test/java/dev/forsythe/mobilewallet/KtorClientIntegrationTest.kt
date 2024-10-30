package dev.forsythe.mobilewallet

import androidx.compose.runtime.internal.composableLambdaInstance
import dev.forsythe.mobilewallet.data.data_source.remote.network.client.KtorClient
import dev.forsythe.mobilewallet.data.data_source.remote.network.client.accountBalance
import dev.forsythe.mobilewallet.data.data_source.remote.network.client.customerLogIn
import dev.forsythe.mobilewallet.data.data_source.remote.network.client.lastHundredTransactions
import dev.forsythe.mobilewallet.data.data_source.remote.network.client.sendMoney
import dev.forsythe.mobilewallet.data.data_source.remote.network.model.request.BalanceRequest
import dev.forsythe.mobilewallet.data.data_source.remote.network.model.request.LastHundredTransactionsRequest
import dev.forsythe.mobilewallet.data.data_source.remote.network.model.request.LogInRequest
import dev.forsythe.mobilewallet.data.data_source.remote.network.model.request.SendMoneyRequest
import dev.forsythe.mobilewallet.data.data_source.remote.network.model.response.LogInResponse
import dev.forsythe.mobilewallet.data.data_source.remote.network.model.response.SendMoneyResponse
import dev.forsythe.mobilewallet.data.data_source.remote.network.model.response.TransactionResponse
import dev.forsythe.mobilewallet.utils.BASE_URL
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
    private val client = KtorClient(baseUrl = BASE_URL)
    @Test
    fun `test successful login` (): Unit = runBlocking {
        val result = client.customerLogIn(
            //logInRequest = LogInRequest(
                customerId = "CUST1001",
                pin = "1234"
            //)
        )
        assertTrue(result.statusCode == HttpStatusCode.OK)
    }

    @Test
    fun `test failed login` (): Unit = runBlocking {
        val result = client.customerLogIn(
                customerId = "CUST100",
                pin = "1234M" // pass wrong pin
        )
        assertTrue(result.statusCode != HttpStatusCode.OK)
        println(result.body.toString())

    }

    @Test
    fun `test successful account balance check` () : Unit = runBlocking {
        val  result = client.accountBalance(
            //balanceRequest = BalanceRequest(
                customerId = "CUST1001",
                accountNo = "ACT1001"
           // )
        )

        assertTrue(result.statusCode == HttpStatusCode.OK)
    }

    @Test
    fun `test failed account balance check` () : Unit = runBlocking {
        val  result = client.accountBalance(
         //   balanceRequest = BalanceRequest(
                customerId = "CUST1001",
                accountNo = "ACT100" //pass wrong account
          //  )
        )
        assertTrue(result.statusCode != HttpStatusCode.OK)
        println(result.body.toString())

    }

    @Test
    fun `test successful last hundred transactions` () : Unit = runBlocking{
        val result = client.lastHundredTransactions(
           // lastHundredTransactionsRequest = LastHundredTransactionsRequest(
                customerId = "CUST1122"
           // )
        )

        assertTrue(result.statusCode == HttpStatusCode.OK)
        val response = result.body as List<TransactionResponse>
        println("transaction type: " + response[0].debitOrCredit)
    }

    @Test
    fun `test failed last hundred transactions` () : Unit = runBlocking{
        val result = client.lastHundredTransactions(
           // lastHundredTransactionsRequest = LastHundredTransactionsRequest(
                customerId = "CUST112" //passed a wrong ID
            //)
        )

        assertTrue(result.statusCode != HttpStatusCode.OK)
        val response = result.body as String
        println(response)
    }

    @Test
    fun `test successful send money` () : Unit = runBlocking {
        val result = client.sendMoney(
            sendMoneyRequest = SendMoneyRequest(
                customerId = "CUST1001",
                accountFrom = "ACT1001",
                accountTo = "ACT1002",
                amount = 5000
            )
        )

        assertTrue( result.statusCode == HttpStatusCode.OK)
        val response = result.body as SendMoneyResponse
        println(response.response_message)
    }
    @Test
    fun `test failed send money` () : Unit = runBlocking {
        val result = client.sendMoney(
            sendMoneyRequest = SendMoneyRequest(
                customerId = "CUST1001",
                accountFrom = "ACT1001JK", //passed a wrong account
                accountTo = "ACT1002",
                amount = 5000
            )
        )

        assertTrue( result.statusCode != HttpStatusCode.OK)
        val response = result.body as String
        println(response)
    }
}