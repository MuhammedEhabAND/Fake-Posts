package mo.inc.eh.fakeposts.domain.usecases

import kotlinx.coroutines.flow.Flow
import mo.inc.eh.fakeposts.data.model.PostResponse
import mo.inc.eh.fakeposts.domain.entity.Post
import mo.inc.eh.fakeposts.domain.repo.Repository
import mo.inc.eh.fakeposts.utils.UiState
import javax.inject.Inject

class GetPostsUseCase @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(): Flow<List<Post>>{
        return repository.getPosts()
    }
}