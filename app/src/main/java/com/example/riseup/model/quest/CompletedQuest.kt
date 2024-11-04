package com.example.riseup.model.quest

data class CompletedQuest(
    val id: Int,
    val name: String,
    val description: String?,
    val type: QuestType,
    val difficulty: QuestDifficulty,
    val completionDate: Long
)
