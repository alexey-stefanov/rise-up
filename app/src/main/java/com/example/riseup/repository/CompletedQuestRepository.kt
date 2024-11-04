package com.example.riseup.repository

import com.example.riseup.model.quest.Quest
import kotlinx.coroutines.flow.Flow

interface CompletedQuestRepository {
    fun getCompletedQuests(): Flow<List<Quest>>
}