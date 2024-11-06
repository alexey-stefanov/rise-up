package com.example.riseup.util

import com.example.riseup.model.achievement.Achievement
import com.example.riseup.model.quest.Quest
import com.example.riseup.model.quest.QuestDifficulty
import com.example.riseup.model.quest.QuestState
import com.example.riseup.model.quest.QuestType

object CommonDataProvider {

    fun provideDailyQuests(): List<Quest> =
        listOf(
            Quest(
                name = "Сделать зарядку",
                description = "",
                type = QuestType.DAILY,
                difficulty = QuestDifficulty.EASY,
                state = QuestState.Accepted
            ),
            Quest(
                name = "Прочитать книгу",
                description = "",
                type = QuestType.DAILY,
                difficulty = QuestDifficulty.MEDIUM,
                state = QuestState.Accepted
            ),
            Quest(
                name = "Медитировать",
                description = "",
                type = QuestType.DAILY,
                difficulty = QuestDifficulty.EASY,
                state = QuestState.Accepted
            ),
            Quest(
                name = "Сходить на пробежку",
                description = "",
                type = QuestType.DAILY,
                difficulty = QuestDifficulty.HARD,
                state = QuestState.Accepted
            ),
            Quest(
                name = "Выпить воды",
                description = "",
                type = QuestType.DAILY,
                difficulty = QuestDifficulty.MEDIUM,
                state = QuestState.Accepted
            )
        )

    fun provideAchievements(): List<Achievement> =
        listOf(
            Achievement(
                name = "Первое задание",
                description = "Выполните первое задание",
                isUnlocked = false,
                requiredSteps = 1
            ),
            Achievement(
                name = "Десять заданий",
                description = "Завершите 10 заданий",
                isUnlocked = false,
                requiredSteps = 10
            ),
            Achievement(
                name = "Сто заданий",
                description = "Завершите 100 заданий",
                isUnlocked = false,
                requiredSteps = 100
            )
        )
}