package com.example.riseup.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "achievements")
data class AchievementEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String,
    val isUnlocked: Boolean,
    val progress: Float = 0f,
    //val icon: ImageVector,
    val requiredSteps: Int,
    val currentStep: Int = 0
)
