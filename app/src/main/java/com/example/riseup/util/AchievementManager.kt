package com.example.riseup.util

import com.example.riseup.model.achievement.Achievement
import javax.inject.Inject

class AchievementManager @Inject constructor() {
    private val achievements: MutableList<Achievement> = mutableListOf()

    init {
        initializeAchievements()
    }

    private fun initializeAchievements() {
        achievements.addAll(
            listOf(
                Achievement(
                    name = "Первый квест",
                    description = "Выполните первый квест",
                    isUnlocked = false,
                    requiredSteps = 1
                ),
                Achievement(
                    name = "Десять квестов",
                    description = "Завершите 10 квестов",
                    isUnlocked = false,
                    requiredSteps = 10
                )
            )
        )
    }

    fun getAchievements(): List<Achievement> = achievements

    fun updateAchievementProgress(achievementId: Int, stepsCompleted: Int = 1) {
        val achievement = achievements.find { it.id == achievementId }
        achievement?.let {
            if (!it.isUnlocked) {
                val newStep = (it.currentStep + stepsCompleted).coerceAtMost(it.requiredSteps)
                val newProgress = newStep.toFloat() / it.requiredSteps
                val isNowUnlocked = newStep >= it.requiredSteps

                achievements[achievements.indexOf(it)] = it.copy(
                    currentStep = newStep,
                    progress = newProgress,
                    isUnlocked = isNowUnlocked
                )
            }
        }
    }

    fun updateAchievementsOnEvent(event: AchievementEvent) {
        when (event) {
            is AchievementEvent.QuestCompleted -> {
                updateAchievementProgress(1)
                updateAchievementProgress(2)
            }

            is AchievementEvent.OtherEvent -> {

            }
        }
    }
}

sealed class AchievementEvent {
    object QuestCompleted : AchievementEvent()
    object OtherEvent : AchievementEvent()
}