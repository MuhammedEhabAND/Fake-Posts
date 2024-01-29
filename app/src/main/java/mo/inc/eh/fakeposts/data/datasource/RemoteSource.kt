package mo.inc.eh.fakeposts.data.datasource

import mo.inc.eh.fakeposts.data.model.PostResponse
import mo.inc.eh.fakeposts.domain.entity.Post

interface RemoteSource {
    suspend fun getPosts() : PostResponse
    suspend fun getPostInDetailed(postId : Int) : Post
}