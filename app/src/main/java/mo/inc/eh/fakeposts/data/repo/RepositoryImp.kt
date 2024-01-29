package mo.inc.eh.fakeposts.data.repo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import mo.inc.eh.fakeposts.data.datasource.RemoteSource
import mo.inc.eh.fakeposts.data.model.PostResponse
import mo.inc.eh.fakeposts.domain.entity.Post
import mo.inc.eh.fakeposts.domain.repo.Repository
import mo.inc.eh.fakeposts.module.network.PostService
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val postService: PostService,
) : Repository {
    override suspend fun getPosts(): Flow<List<Post>> {

        return flow {
            val posts = postService.getPosts()
            emit(posts)
        }
    }

    override suspend fun getPostInDetails(postId: Int): Flow<Post> {
        return flow {
            val post = postService.getPostDetails(postId)
            emit(post)
        }
    }

}