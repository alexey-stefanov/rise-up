package com.example.riseup.di

import android.content.Context
import android.content.SharedPreferences
import com.example.riseup.util.DailyQuestManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UtilModule {

    @Provides
    @Singleton
    fun provideSharedPreferences(@ApplicationContext context: Context): SharedPreferences {
        return context.getSharedPreferences("daily_quests_prefs", Context.MODE_PRIVATE)
    }

    @Provides
    @Singleton
    fun provideDailyQuestManager(sharedPreferences: SharedPreferences): DailyQuestManager {
        return DailyQuestManager(sharedPreferences)
    }
}