package dev.forsythe.mobilewallet.di

import android.app.Application
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import dev.forsythe.mobilewallet.data.local.room.MobileWalletDatabase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesMobileWalletDatabase( application: Application) : MobileWalletDatabase{
        return MobileWalletDatabase.getInstance(application)
    }
}