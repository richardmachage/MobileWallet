package dev.forsythe.mobilewallet.data.data_source.local.room.entities.transaction

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface TransactionDao {
    @Insert
    suspend fun addTransaction(transaction: Transaction) : Long

    @Update
    suspend fun updateTransaction(transaction: Transaction)

    @Delete
    suspend fun deleteTransaction(transaction: Transaction)

    @Query("SELECT * FROM transactions_tbl ORDER BY timestamp DESC")
    fun getAllTransactions() : Flow<List<Transaction>>

    @Query("UPDATE transactions_tbl SET status = :status WHERE id = :id")
    fun updateTransactionStatus(status : String, id: Int);
}