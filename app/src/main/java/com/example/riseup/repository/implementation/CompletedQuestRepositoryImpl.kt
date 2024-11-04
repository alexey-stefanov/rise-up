package com.example.riseup.repository.implementation

import com.example.riseup.data.local.dao.QuestDao
import com.example.riseup.model.quest.Quest
import com.example.riseup.repository.CompletedQuestRepository
import com.example.riseup.util.QuestMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CompletedQuestRepositoryImpl @Inject constructor(
    private val questDao: QuestDao
) : CompletedQuestRepository {
    override fun getCompletedQuests(): Flow<List<Quest>> {
        return questDao.getCompletedQuests().map { entities ->
            entities.map {
                QuestMapper.fromEntity(it)
            }.reversed()
        }
    }
}