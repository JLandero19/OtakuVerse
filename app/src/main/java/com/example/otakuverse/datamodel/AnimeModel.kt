package com.example.otakuverse.datamodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Entity(tableName = "animes")
data class AnimeModel(
    @PrimaryKey(autoGenerate = true)
    val myanimelist_id: Int,
    @ColumnInfo(name = "aired_on")
    val aired_on: String,
    @ColumnInfo(name = "members")
    val members: Int,
    @ColumnInfo(name = "myanimelist_url")
    val myanimelist_url: String,
    @ColumnInfo(name = "picture_url")
    val picture_url: String,
    @ColumnInfo(name = "rank")
    val rank: Int,
    @ColumnInfo(name = "score")
    val score: Double,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "type")
    val type: String
)