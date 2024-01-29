package mo.inc.eh.fakeposts.domain.repo

import kotlinx.coroutines.flow.Flow
import mo.inc.eh.fakeposts.data.model.PostResponse
import mo.inc.eh.fakeposts.domain.entity.Post

interface Repository {
    suspend fun getPosts(): Flow<List<Post>>
    suspend fun getPostInDetails (postId : Int) : Flow<Post>
}