package com.example.riseup.model.quest

data class Quest(
    val id: Int,
    val name: String,
    val description: String? = null,
    val type: QuestType,
    var isAccepted: Boolean = false,
    var isCompleted: Boolean = false,
    val difficulty: QuestDifficulty
)
