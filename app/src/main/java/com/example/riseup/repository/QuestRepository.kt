package com.example.riseup.repository

import com.example.riseup.model.quest.Quest
import kotlinx.coroutines.flow.Flow

interface QuestRepository {
    fun getActiveQuests(): Flow<List<Quest>>
    suspend fun insertQuest(quest: Quest)
    suspend fun updateQuest(quest: Quest)
    suspend fun deleteQuest(quest: Quest)
    suspend fun initializeDailyQuests()
}