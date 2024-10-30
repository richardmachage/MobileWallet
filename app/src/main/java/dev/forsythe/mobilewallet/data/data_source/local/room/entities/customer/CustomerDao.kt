package dev.forsythe.mobilewallet.data.data_source.local.room.entities.customer

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CustomerDao {
    @Insert
    suspend fun addCustomer(customer: Customer)

    @Query("DELETE FROM customer_tbl WHERE customerId = :customerId")
    suspend fun deleteCustomer(customerId: String)

    @Query("SELECT * FROM customer_tbl")
    fun getCustomerDetails(): Flow<Customer>

}