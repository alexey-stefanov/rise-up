package com.example.riseup.model.character

data class Character(
    val id: Int = 1,
    val level: Int = 1,
    val currentXp: Int = 0,
    val xpForNextLevel: Int = 100,
    val achievements: List<String> = emptyList()
)
