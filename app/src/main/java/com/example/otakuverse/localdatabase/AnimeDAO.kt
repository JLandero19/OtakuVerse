package com.example.otakuverse.localdatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.otakuverse.datamodel.Anime
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertAnime(anime: Anime)

    @Delete
    suspend fun deleteAnime(anime: Anime)

    @Update
    suspend fun update(anime: Anime)

    @Query("SELECT * from animes")
    fun getAllAnimes(): Flow<List<Anime>>
}