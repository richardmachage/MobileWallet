package dev.forsythe.mobilewallet.data.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import dev.forsythe.mobilewallet.data.local.room.entities.transaction.Transaction
import dev.forsythe.mobilewallet.data.local.room.entities.transaction.TransactionDao

@Database(
    entities = [Transaction::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(MobileWalletDbConverters::class)

abstract class MobileWalletDatabase : RoomDatabase(){

    abstract fun transactionDao() : TransactionDao

    companion object{

        private var INSTANCE : MobileWalletDatabase? = null

        fun getInstance(context: Context):MobileWalletDatabase{
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