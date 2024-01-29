package mo.inc.eh.fakeposts.data.datasource

import mo.inc.eh.fakeposts.data.model.PostResponse
import mo.inc.eh.fakeposts.domain.entity.Post
import mo.inc.eh.fakeposts.network.PostService
import javax.inject.Inject

class RemoteSourceImp @Inject constructor(
    private val postService : PostService
) : RemoteSource{
    override suspend fun getPosts(): PostResponse {
       return postService.getPosts()
    }

    override suspend fun getPostInDetailed(postId: Int): Post {
        return postService.getPostDetails(postId)
    }
}