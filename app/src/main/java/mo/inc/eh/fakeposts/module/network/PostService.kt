package mo.inc.eh.fakeposts.module.network

import mo.inc.eh.fakeposts.data.model.PostResponse
import mo.inc.eh.fakeposts.domain.entity.Post
import retrofit2.http.GET
import retrofit2.http.Path

interface PostService {
    @GET("posts")
    suspend fun getPosts(): List<Post>

    @GET("posts/{postId}")
    suspend fun getPostDetails(@Path("postId") postId: Int): Post

}