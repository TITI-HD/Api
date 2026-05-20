package com.example.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "requests")
data class RequestEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val url: String,
    val method: String = "GET",
    val headersJson: String = "[]",
    val body: String = "",
    val timestamp: Long = System.currentTimeMillis()
)

@Entity(tableName = "flows")
data class FlowEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val nodesJson: String = "[]", // List of steps
    val timestamp: Long = System.currentTimeMillis()
)
