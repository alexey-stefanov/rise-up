package com.example.riseup.repository

import com.example.riseup.model.quest.Quest
import kotlinx.coroutines.flow.Flow

interface QuestRepository {
    fun getAllQuests(): Flow<List<Quest>>
    suspend fun insertQuest(quest: Quest)
    suspend fun deleteQuest(quest: Quest)
    suspend fun updateQuest(quest: Quest)
    suspend fun initializeDailyQuests()
    suspend fun acceptQuest(quest: Quest)
    suspend fun declineQuest(quest: Quest)
    suspend fun completeQuest(quest: Quest)
}