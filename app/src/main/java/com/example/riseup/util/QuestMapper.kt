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
            state = entity.state
        )
    }

    fun toEntity(model: Quest): QuestEntity {
        return QuestEntity(
            id = model.id,
            name = model.name,
            description = model.description,
            type = model.type,
            difficulty = model.difficulty,
            state = model.state
        )
    }
}