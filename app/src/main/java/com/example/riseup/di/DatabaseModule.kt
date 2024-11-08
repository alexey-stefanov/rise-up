package com.example.riseup.di

import android.content.Context
import androidx.room.Room
import com.example.riseup.data.local.AppDatabase
import com.example.riseup.data.local.dao.AchievementDao
import com.example.riseup.data.local.dao.CharacterDao
import com.example.riseup.data.local.dao.QuestDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "app_database"
        ).build()
    }

    @Provides
    fun provideQuestDao(database: AppDatabase): QuestDao = database.questDao()

    @Provides
    fun provideCharacterDao(database: AppDatabase): CharacterDao = database.characterDao()

    @Provides
    fun provideAchievementDao(database: AppDatabase): AchievementDao = database.achievementDao()
}