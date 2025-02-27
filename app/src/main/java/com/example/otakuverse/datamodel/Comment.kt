package com.example.otakuverse.datamodel

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comments")
data class Comment (
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val comment: String,
    val user: String,
    val image: String = "",
    val anime_id: Int
)