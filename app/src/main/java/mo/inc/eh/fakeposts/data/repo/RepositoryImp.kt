package mo.inc.eh.fakeposts.data.repo

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import mo.inc.eh.fakeposts.data.datasource.RemoteSource
import mo.inc.eh.fakeposts.data.model.PostResponse
import mo.inc.eh.fakeposts.domain.entity.Post
import mo.inc.eh.fakeposts.domain.repo.Repository
import javax.inject.Inject

class RepositoryImp @Inject constructor(
    private val remoteSource: RemoteSource,
) : Repository {
    override suspend fun getPosts(): Flow<PostResponse> {

        return flow {
            val posts = remoteSource.getPosts()
            emit(posts)
        }
    }

    override suspend fun getPostInDetails(postId: Int): Flow<Post> {
        return flow {
            val post = remoteSource.getPostInDetailed(postId)
            emit(post)
        }
    }

}