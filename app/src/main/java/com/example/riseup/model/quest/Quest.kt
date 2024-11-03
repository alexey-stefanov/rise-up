package com.example.riseup.model.quest

data class Quest(
    val id: Int,
    val name: String,
    val description: String? = null,
    val type: QuestType,
    var isAccepted: Boolean = false,
    var isCompleted: Boolean = false,
    val difficulty: QuestDifficulty,
    val xp: Int = when (difficulty) {
        QuestDifficulty.EASY -> 10
        QuestDifficulty.MEDIUM -> 20
        QuestDifficulty.HARD -> 30
    }
)
