package com.example.riseup.repository

import com.example.riseup.data.local.entities.AchievementEntity
import com.example.riseup.model.achievement.Achievement
import com.example.riseup.util.AchievementEvent
import kotlinx.coroutines.flow.Flow

interface AchievementRepository {
    fun getAllAchievements(): Flow<List<Achievement>?>
    suspend fun initializeAchievementsIfNeeded()
    suspend fun insertAchievement(achievement: AchievementEntity)
    suspend fun updateAchievement(achievement: AchievementEntity)
    suspend fun updateAchievementsOnEvent(event: AchievementEvent)
}