package com.example.riseup.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.riseup.data.local.dao.CharacterDao
import com.example.riseup.data.local.dao.QuestDao
import com.example.riseup.data.local.entities.CharacterEntity
import com.example.riseup.data.local.entities.QuestEntity

@Database(entities = [QuestEntity::class, CharacterEntity::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun questDao(): QuestDao
    abstract fun characterDao(): CharacterDao
}