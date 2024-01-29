package mo.inc.eh.fakeposts.utils

sealed class UiState<out T>{
    object OnLoading : UiState<Nothing> ()
    data class OnSuccess<T>(val data : T): UiState<T>()
    data class OnFailure<T>(val exception: Exception): UiState<Nothing>()
}
