package com.example.riseup.repository.implementation

import com.example.riseup.data.local.dao.CompletedQuestDao
import com.example.riseup.model.quest.CompletedQuest
import com.example.riseup.model.quest.Quest
import com.example.riseup.repository.CompletedQuestRepository
import com.example.riseup.util.CompletedQuestMapper
import com.example.riseup.util.QuestMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CompletedQuestRepositoryImpl @Inject constructor(
    private val completedQuestDao: CompletedQuestDao
) : CompletedQuestRepository {
    override fun getCompletedQuests(): Flow<List<CompletedQuest>> {
        return completedQuestDao.getCompletedQuests().map { entities ->
            entities.map { CompletedQuestMapper.fromEntity(it) }
        }
    }

    override suspend fun addCompletedQuest(quest: Quest) {
        val completedQuest = QuestMapper.toCompletedQuest(quest)
        val completedQuestEntity = CompletedQuestMapper.toEntity(completedQuest)
        completedQuestDao.insertCompletedQuest(completedQuestEntity)
    }
}