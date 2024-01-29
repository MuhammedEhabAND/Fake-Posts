package mo.inc.eh.fakeposts.module.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mo.inc.eh.fakeposts.data.repo.RepositoryImp
import mo.inc.eh.fakeposts.domain.repo.Repository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepoModule {
    @Provides
    fun providesRepo(postService: PostService): Repository{
        return RepositoryImp(postService)
    }
}