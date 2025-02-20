package com.example.otakuverse.datamodel

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@Entity(tableName = "animes")
data class Anime(
    @PrimaryKey(autoGenerate = true)
    @SerialName("myanimelist_id") val myanimelist_id: Int,

    @ColumnInfo(name = "aired_on")
    @SerialName("aired_on") val aired_on: String,

    @ColumnInfo(name = "members")
    @SerialName("members") val members: Int,

    @ColumnInfo(name = "myanimelist_url")
    @SerialName("myanimelist_url") val myanimelist_url: String,

    @ColumnInfo(name = "picture_url")
    @SerialName("picture_url") val picture_url: String,

    @ColumnInfo(name = "rank")
    @SerialName("rank") val rank: Int,

    @ColumnInfo(name = "score")
    @SerialName("score") val score: Double,

    @ColumnInfo(name = "title")
    @SerialName("title") val title: String,

    @ColumnInfo(name = "type")
    @SerialName("type") val type: String
)