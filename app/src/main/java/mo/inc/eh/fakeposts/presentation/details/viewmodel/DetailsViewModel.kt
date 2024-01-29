package mo.inc.eh.fakeposts.presentation.details.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import mo.inc.eh.fakeposts.domain.entity.Post
import mo.inc.eh.fakeposts.domain.usecases.GetPostInDetails
import mo.inc.eh.fakeposts.utils.UiState
import javax.inject.Inject
@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getPostInDetails: GetPostInDetails
) : ViewModel() {
    private var _detailedPost :MutableStateFlow<UiState<Post>>  = MutableStateFlow(UiState.OnLoading)
    val detailedPost : StateFlow<UiState<Post>>
        get() = _detailedPost
    fun getPostInDetails(postId :Int){
        viewModelScope.launch(Dispatchers.IO) {
            getPostInDetails.invoke(postId).catch {e->
                _detailedPost.value = UiState.OnFailure<Nothing>(e.localizedMessage.toString())
            }.collect{data ->
                _detailedPost.value = UiState.OnSuccess<Post>(data)

            }
        }
    }
}