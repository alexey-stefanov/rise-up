package com.example.riseup.util

import com.example.riseup.data.local.entities.QuestEntity
import com.example.riseup.model.quest.Quest

object QuestMapper {

    fun fromEntity(entity: QuestEntity): Quest {
        return Quest(
            id = entity.id,
            name = entity.name,
            description = entity.description,
            type = entity.type,
            difficulty = entity.difficulty,
            xp = entity.xp,
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
            xp = model.xp,
            isAccepted = model.isAccepted,
            isCompleted = model.isCompleted
        )
    }
}