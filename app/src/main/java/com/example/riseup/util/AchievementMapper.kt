package com.example.riseup.util

import com.example.riseup.data.local.entities.AchievementEntity
import com.example.riseup.model.achievement.Achievement

object AchievementMapper {

    fun fromEntity(entity: AchievementEntity): Achievement {
        return Achievement(
            id = entity.id,
            name = entity.name,
            description = entity.description,
            isUnlocked = entity.isUnlocked,
            progress = entity.progress,
            requiredSteps = entity.requiredSteps,
            currentStep = entity.currentStep
        )
    }

    fun toEntity(model: Achievement): AchievementEntity {
        return AchievementEntity(
            id = model.id,
            name = model.name,
            description = model.description,
            isUnlocked = model.isUnlocked,
            progress = model.progress,
            requiredSteps = model.requiredSteps,
            currentStep = model.currentStep
        )
    }
}