package com.example.riseup.model.quest

data class Quest(
    val id: Int,
    val name: String,
    val description: String? = null,
    val type: QuestType,
    val difficulty: QuestDifficulty,
    var state: QuestState = QuestState.New
)
