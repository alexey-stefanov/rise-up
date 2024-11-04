package com.example.riseup.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.riseup.model.quest.QuestDifficulty
import com.example.riseup.model.quest.QuestType

@Entity(tableName = "completed_quests")
data class CompletedQuestEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val name: String,
    val description: String?,
    val type: QuestType,
    val difficulty: QuestDifficulty,
    val completionDate: Long
)
