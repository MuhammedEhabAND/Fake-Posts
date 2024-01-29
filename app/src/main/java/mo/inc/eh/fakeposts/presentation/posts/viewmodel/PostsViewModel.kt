package mo.inc.eh.fakeposts.presentation.posts.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import mo.inc.eh.fakeposts.data.model.PostResponse
import mo.inc.eh.fakeposts.domain.entity.Post
import mo.inc.eh.fakeposts.domain.usecases.GetPostsUseCase
import mo.inc.eh.fakeposts.utils.UiState
import javax.inject.Inject
@HiltViewModel
class PostsViewModel @Inject constructor(
    private val getPostsUseCase: GetPostsUseCase
) : ViewModel() {
    private var _posts : MutableStateFlow<UiState<List<Post>>> = MutableStateFlow(UiState.OnLoading)
    val posts:StateFlow<UiState<List<Post>>>
        get() =_posts
    init {
        viewModelScope.launch(Dispatchers.IO) {
            getPostsUseCase.invoke().catch { e->
                _posts.value = UiState.OnFailure<Nothing>(e.localizedMessage.toString())
            }.collect{data->
                _posts.value = UiState.OnSuccess<List<Post>>(data)
            }
        }
    }
}