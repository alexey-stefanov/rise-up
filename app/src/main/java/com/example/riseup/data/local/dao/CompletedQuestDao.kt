package com.example.riseup.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.riseup.data.local.entities.CompletedQuestEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CompletedQuestDao {

    @Query("SELECT * FROM completed_quests ORDER BY completionDate DESC")
    fun getCompletedQuests(): Flow<List<CompletedQuestEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertCompletedQuest(quest: CompletedQuestEntity)
}