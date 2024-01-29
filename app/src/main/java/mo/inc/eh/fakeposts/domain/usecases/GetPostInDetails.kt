package mo.inc.eh.fakeposts.domain.usecases

import kotlinx.coroutines.flow.Flow
import mo.inc.eh.fakeposts.domain.entity.Post
import mo.inc.eh.fakeposts.domain.repo.Repository
import javax.inject.Inject

class GetPostInDetails @Inject constructor(
    private val repository: Repository
) {
    suspend operator fun invoke(postId : Int): Flow<Post>{
        return repository.getPostInDetails(postId)
    }

}