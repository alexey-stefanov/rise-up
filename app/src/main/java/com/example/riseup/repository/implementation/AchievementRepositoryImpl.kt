package com.example.riseup.repository.implementation

import com.example.riseup.data.local.dao.AchievementDao
import com.example.riseup.data.local.entities.AchievementEntity
import com.example.riseup.model.achievement.Achievement
import com.example.riseup.repository.AchievementRepository
import com.example.riseup.util.AchievementEvent
import com.example.riseup.util.AchievementManager
import com.example.riseup.util.AchievementMapper
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class AchievementRepositoryImpl @Inject constructor(
    private val achievementDao: AchievementDao,
    private val achievementManager: AchievementManager
) : AchievementRepository {
    override fun getAllAchievements(): Flow<List<Achievement>?> {
        return achievementDao.getAllAchievements().map { entities ->
            entities.map { AchievementMapper.fromEntity(it) }
        }
    }

    // TODO need kind of initializer for all classes
    override suspend fun initializeAchievementsIfNeeded() {
        val achievements = achievementManager.getAchievements().map { AchievementMapper.toEntity(it) }
        achievements.forEach { insertAchievement(it) }
    }

    override suspend fun insertAchievement(achievement: AchievementEntity) {
        achievementDao.insertAchievement(achievement)
    }

    override suspend fun updateAchievement(achievement: AchievementEntity) {
        achievementDao.updateAchievement(achievement)
    }

    override suspend fun updateAchievementsOnEvent(event: AchievementEvent) {
        achievementManager.updateAchievementsOnEvent(event)
    }
}