package com.example.otakuverse.localdatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.otakuverse.datamodel.AnimeModel
import kotlinx.coroutines.flow.Flow

@Dao
interface AnimeDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertAnime(anime: AnimeModel)

    @Delete
    suspend fun deleteAnime(anime: AnimeModel)

    @Update
    suspend fun update(anime: AnimeModel)

    @Query("SELECT * from animes")
    fun getAllAnimes(): Flow<List<AnimeModel>>
}