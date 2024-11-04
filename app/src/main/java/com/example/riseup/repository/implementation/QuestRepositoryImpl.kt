package com.example.riseup.repository.implementation

import com.example.riseup.data.local.dao.QuestDao
import com.example.riseup.model.quest.Quest
import com.example.riseup.repository.QuestRepository
import com.example.riseup.util.DailyQuestManager
import com.example.riseup.util.QuestMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class QuestRepositoryImpl @Inject constructor(
    private val questDao: QuestDao,
    private val dailyQuestManager: DailyQuestManager
) : QuestRepository {
    override fun getAllQuests(): Flow<List<Quest>> {
        return questDao.getAllQuests().map { entities ->
            entities.map { QuestMapper.fromEntity(it) }
        }
    }

    override suspend fun insertQuest(quest: Quest) {
        questDao.insertQuest(QuestMapper.toEntity(quest))
    }

    override suspend fun updateQuest(quest: Quest) {
        questDao.updateQuest(QuestMapper.toEntity(quest))
    }

    override suspend fun deleteQuest(quest: Quest) {
        questDao.deleteQuest(QuestMapper.toEntity(quest))
    }

    override suspend fun initializeDailyQuests() {
        if (dailyQuestManager.shouldInitializeDailyQuests()) {
            val oldDailyQuests = dailyQuestManager.getDailyQuests()

            // xp penalty logic

            oldDailyQuests.forEach { deleteQuest(it) }
            val newDailyQuests = dailyQuestManager.getDailyQuests()
            newDailyQuests.forEach { insertQuest(it) }
        }
    }
}