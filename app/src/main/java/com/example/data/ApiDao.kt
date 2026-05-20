package com.example.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface ApiDao {
    @Query("SELECT * FROM requests ORDER BY timestamp DESC")
    fun getAllRequests(): Flow<List<RequestEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertRequest(req: RequestEntity)

    @Query("DELETE FROM requests WHERE id = :id")
    suspend fun deleteRequestById(id: Int)

    @Query("SELECT * FROM flows ORDER BY timestamp DESC")
    fun getAllFlows(): Flow<List<FlowEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFlow(flow: FlowEntity)

    @Query("DELETE FROM flows WHERE id = :id")
    suspend fun deleteFlowById(id: Int)
}
