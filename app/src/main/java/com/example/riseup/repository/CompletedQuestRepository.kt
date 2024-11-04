package com.example.riseup.repository

import com.example.riseup.model.quest.CompletedQuest
import com.example.riseup.model.quest.Quest
import kotlinx.coroutines.flow.Flow

interface CompletedQuestRepository {
    fun getCompletedQuests(): Flow<List<CompletedQuest>>
    suspend fun addCompletedQuest(quest: Quest)
}