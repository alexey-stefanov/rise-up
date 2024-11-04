package com.example.riseup.util

import com.example.riseup.data.local.entities.CompletedQuestEntity
import com.example.riseup.model.quest.CompletedQuest

object CompletedQuestMapper {

    fun fromEntity(entity: CompletedQuestEntity): CompletedQuest {
        return CompletedQuest(
            id = entity.id,
            name = entity.name,
            description = entity.description,
            type = entity.type,
            difficulty = entity.difficulty,
            completionDate = entity.completionDate
        )
    }

    fun toEntity(model: CompletedQuest): CompletedQuestEntity {
        return CompletedQuestEntity(
            id = model.id,
            name = model.name,
            description = model.description,
            type = model.type,
            difficulty = model.difficulty,
            completionDate = model.completionDate
        )
    }
}