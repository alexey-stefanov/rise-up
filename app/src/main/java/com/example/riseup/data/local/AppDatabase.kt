package com.example.riseup.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.riseup.data.local.dao.AchievementDao
import com.example.riseup.data.local.dao.CharacterDao
import com.example.riseup.data.local.dao.QuestDao
import com.example.riseup.data.local.entities.AchievementEntity
import com.example.riseup.data.local.entities.CharacterEntity
import com.example.riseup.data.local.entities.QuestEntity
import com.example.riseup.util.QuestStateConverter

@Database(entities = [QuestEntity::class, CharacterEntity::class, AchievementEntity::class], version = 1)
@TypeConverters(QuestStateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun questDao(): QuestDao
    abstract fun characterDao(): CharacterDao
    abstract fun achievementDao(): AchievementDao
}