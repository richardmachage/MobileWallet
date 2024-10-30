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
}