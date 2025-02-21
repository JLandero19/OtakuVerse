package com.example.otakuverse.datamodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "animes")
data class AnimeModel(
    @PrimaryKey
    @ColumnInfo(name = "myanimelist_id")
    val myanimelistId: Int,
    @ColumnInfo(name = "aired_on")
    val airedOn: String,
    @ColumnInfo(name = "members")
    val members: Int,
    @ColumnInfo(name = "myanimelist_url")
    val myanimelistUrl: String,
    @ColumnInfo(name = "picture_url")
    val pictureUrl: String,
    @ColumnInfo(name = "rank")
    val rank: Int,
    @ColumnInfo(name = "score")
    val score: Double,
    @ColumnInfo(name = "title")
    val title: String,
    @ColumnInfo(name = "type")
    val type: String
)