package com.example.riseup.di

import com.example.riseup.repository.CharacterRepository
import com.example.riseup.repository.CompletedQuestRepository
import com.example.riseup.repository.QuestRepository
import com.example.riseup.repository.implementation.CharacterRepositoryImpl
import com.example.riseup.repository.implementation.CompletedQuestRepositoryImpl
import com.example.riseup.repository.implementation.QuestRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindQuestRepository(
        questRepositoryImpl: QuestRepositoryImpl
    ): QuestRepository

    @Binds
    @Singleton
    abstract fun bindCharacterRepository(
        characterRepositoryImpl: CharacterRepositoryImpl
    ): CharacterRepository

    @Binds
    @Singleton
    abstract fun bindCompletedQuestRepository(
        completedQuestRepositoryImpl: CompletedQuestRepositoryImpl
    ): CompletedQuestRepository
}