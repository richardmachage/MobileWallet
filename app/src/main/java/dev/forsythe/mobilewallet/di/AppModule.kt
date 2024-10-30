package dev.forsythe.mobilewallet.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.forsythe.mobilewallet.data.data_source.local.preferences.PreferenceStore
import dev.forsythe.mobilewallet.data.data_source.local.room.MobileWalletDatabase
import dev.forsythe.mobilewallet.data.data_source.local.room.entities.customer.CustomerDao
import dev.forsythe.mobilewallet.data.data_source.local.room.entities.transaction.TransactionDao
import dev.forsythe.mobilewallet.data.data_source.remote.network.client.KtorClient
import dev.forsythe.mobilewallet.data.repository_impl.CustomerRepoImpl
import dev.forsythe.mobilewallet.data.repository_impl.TransactionsRepoImpl
import dev.forsythe.mobilewallet.domain.repository.CustomerRepo
import dev.forsythe.mobilewallet.domain.repository.TransactionsRepo
import java.util.prefs.Preferences
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesMobileWalletDatabase( application: Application) : MobileWalletDatabase {
        return MobileWalletDatabase.getInstance(application)
    }

    @Provides
    @Singleton
    fun providePreferenceStore(@ApplicationContext context : Context) : PreferenceStore{
        return PreferenceStore(context)
    }

    @Provides
    @Singleton
    fun providesKtorClient() : KtorClient = KtorClient()

    @Provides
    @Singleton
    fun providesCustomerDao (db : MobileWalletDatabase) : CustomerDao {
        return db.customerDao()
    }

    @Provides
    @Singleton
    fun providesTransactionDao (db : MobileWalletDatabase) : TransactionDao {
        return db.transactionDao()
    }

    @Provides
    @Singleton
    fun providesTransactionsRepo(ktorClient: KtorClient, transactionsDao: TransactionDao, customerDao: CustomerDao) : TransactionsRepo{
        return TransactionsRepoImpl(ktorClient = ktorClient, transactionDao = transactionsDao, customerDao = customerDao)
    }

    @Provides
    @Singleton
    fun providesCustomerRepo(ktorClient: KtorClient, customerDao: CustomerDao, preferenceStore: PreferenceStore): CustomerRepo{
        return CustomerRepoImpl(ktorClient = ktorClient, customerDao = customerDao, preferenceStore = preferenceStore)
    }
}