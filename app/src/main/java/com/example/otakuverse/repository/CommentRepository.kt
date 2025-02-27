package com.example.otakuverse.repository

import com.example.otakuverse.datamodel.Comment
import com.example.otakuverse.localdatabase.CommentDAO
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class CommentRepository(
    private val commentDAO: CommentDAO
) {
    suspend fun insertComment(comment: Comment) = commentDAO.insertComment(comment)

    suspend fun deleteComment(comment: Comment) = commentDAO.deleteComment(comment)

    suspend fun update(comment: Comment) = commentDAO.update(comment)

    fun getAllComments(): Flow<List<Comment>> = commentDAO.getAllComments()

    fun getCommentAnime(animeId: Int): Flow<List<Comment>> {
        return getAllComments().map { comments ->
            comments.filter { it.anime_id == animeId }
        }
    }
}