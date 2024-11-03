package com.example.riseup.data.local.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character")
data class CharacterEntity(
    @PrimaryKey
    val id: Int = 1,
    val level: Int = 1,
    val currentXp: Int = 0,
    val xpForNextLevel: Int = 100,
)
