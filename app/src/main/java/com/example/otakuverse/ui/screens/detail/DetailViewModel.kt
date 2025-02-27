package com.example.otakuverse.ui.screens.detail


import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.example.otakuverse.data.UserPreferencesRepository
import com.example.otakuverse.datamodel.Comment
import com.example.otakuverse.otakuverserelease.OtakuverseReleaseApplication
import com.example.otakuverse.repository.AnimeRepository
import com.example.otakuverse.repository.CommentRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class DetailViewModel (
    private val animeRepository: AnimeRepository,
    private val commentRepository: CommentRepository,
) : ViewModel() {

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application = (this[APPLICATION_KEY] as OtakuverseReleaseApplication)
                DetailViewModel(
                    application.animeRepository,
                    application.commentRepository
                )
            }
        }
    }

    private val _uiState = MutableStateFlow(
        DetailUiState()
    )

    val uiState: StateFlow<DetailUiState> = _uiState

    fun animeDetail(id: Int) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true) // Mostrar estado de carga
            try {
                // Obtener el anime y los comentarios de forma asíncrona
                val anime = animeRepository.getAnime(id)
                // Manejar el resultado de la operación
                anime.onSuccess { animeDetail ->
                    // Si el resultado es exitoso, obtenemos el AnimeDetail
                    commentRepository.getCommentAnime(id).collect { commentList ->
                        // Actualizamos el estado con los datos obtenidos
                        _uiState.value = DetailUiState(
                            anime = animeDetail,  // Asignamos el AnimeDetail
                            isLoading = false,    // Indicamos que ya no está cargando
                            comment = commentList.toMutableList() // Los comentarios relacionados con el anime
                        )
                    }
                }.onFailure { exception ->
                    // Si hay un error, mostramos un mensaje de error
                    _uiState.value = DetailUiState(isLoading = false, errorMessage = exception.message)
                }
            } catch (e: Exception) {
                _uiState.value = DetailUiState(isLoading = false, errorMessage = e.message)
            }
        }
    }

    fun addComment(comment: Comment) {
        viewModelScope.launch {
            try {
                commentRepository.insertComment(comment)
                _uiState.update { currentState ->
                    val updatedComment = currentState.comment.toMutableList()  // Convertir la lista inmutable a mutable
                    updatedComment.add(comment)  // Agregar el nuevo anime
                    currentState.copy(
                        comment = updatedComment  // Usamos el operador + para agregar el elemento
                    )
                }
            } catch (e: Exception) {
                // Aquí puedes manejar cualquier error de inserción o mostrar un mensaje al usuario
                Log.d("SaveComment", "Error al guardar el comentario: ${e.message}")
            }
        }
    }
}