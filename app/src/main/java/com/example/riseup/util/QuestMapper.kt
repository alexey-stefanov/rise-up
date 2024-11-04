package com.example.riseup.util

import com.example.riseup.data.local.entities.QuestEntity
import com.example.riseup.model.quest.CompletedQuest
import com.example.riseup.model.quest.Quest

object QuestMapper {

    fun fromEntity(entity: QuestEntity): Quest {
        return Quest(
            id = entity.id,
            name = entity.name,
            description = entity.description,
            type = entity.type,
            difficulty = entity.difficulty,
            isAccepted = entity.isAccepted,
            isCompleted = entity.isCompleted
        )
    }

    fun toEntity(model: Quest): QuestEntity {
        return QuestEntity(
            id = model.id,
            name = model.name,
            description = model.description,
            type = model.type,
            difficulty = model.difficulty,
            isAccepted = model.isAccepted,
            isCompleted = model.isCompleted
        )
    }

    fun toCompletedQuest(quest: Quest): CompletedQuest {
        return CompletedQuest(
            id = quest.id,
            name = quest.name,
            description = quest.description,
            type = quest.type,
            difficulty = quest.difficulty,
            completionDate = System.currentTimeMillis()
        )
    }
}