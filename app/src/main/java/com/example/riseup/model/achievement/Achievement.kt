package com.example.riseup.model.achievement

data class Achievement(
    val id: Int = 0,
    val name: String,
    val description: String,
    val isUnlocked: Boolean,
    val progress: Float = 0f,
    //val icon: ImageVector,
    val requiredSteps: Int,
    val currentStep: Int = 0
)
