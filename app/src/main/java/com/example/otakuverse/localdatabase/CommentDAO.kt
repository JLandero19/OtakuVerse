package com.example.otakuverse.localdatabase

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.otakuverse.datamodel.Comment
import kotlinx.coroutines.flow.Flow

@Dao
interface CommentDAO {
    @Insert(onConflict = OnConflictStrategy.ABORT)
    suspend fun insertComment(comment: Comment)

    @Delete
    suspend fun deleteComment(comment: Comment)

    @Update
    suspend fun update(comment: Comment)

    @Query("SELECT * from comments")
    fun getAllComments(): Flow<List<Comment>>
}