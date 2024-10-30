package dev.forsythe.mobilewallet.data.data_source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.forsythe.mobilewallet.data.data_source.local.room.entities.customer.Customer
import dev.forsythe.mobilewallet.data.data_source.local.room.entities.customer.CustomerDao
import dev.forsythe.mobilewallet.data.data_source.local.room.entities.transaction.Transaction
import dev.forsythe.mobilewallet.data.data_source.local.room.entities.transaction.TransactionDao

@Database(
    entities = [Transaction::class, Customer::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(MobileWalletDbConverters::class)

abstract class MobileWalletDatabase : RoomDatabase(){

    abstract fun transactionDao() : TransactionDao
    abstract fun customerDao() : CustomerDao

    companion object{

        private var INSTANCE : MobileWalletDatabase? = null

        fun getInstance(context: Context): MobileWalletDatabase {
            synchronized(this){
                var instance  = INSTANCE
                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        MobileWalletDatabase::class.java,
                        "mobile_wallet_database"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return  instance
            }
        }
    }
}