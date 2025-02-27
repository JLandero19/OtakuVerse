package com.example.otakuverse.repository

import com.example.otakuverse.datamodel.Comment
import kotlinx.coroutines.flow.Flow

interface AnimeInterface {
    suspend fun insertComment(comment: Comment)

    suspend fun deleteComment(comment: Comment)

    suspend fun update(comment: Comment)

    fun getAllComments(): Flow<List<Comment>>
}