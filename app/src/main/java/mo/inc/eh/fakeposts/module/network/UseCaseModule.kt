package mo.inc.eh.fakeposts.module.network

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import mo.inc.eh.fakeposts.domain.repo.Repository
import mo.inc.eh.fakeposts.domain.usecases.GetPostInDetails
import mo.inc.eh.fakeposts.domain.usecases.GetPostsUseCase

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    @Provides
    fun provideGetPostsUseCase(repository: Repository): GetPostsUseCase{
        return GetPostsUseCase(repository)
    }
    @Provides
    fun provideGetPostInDetailsUseCase(repository: Repository): GetPostInDetails{
        return GetPostInDetails(repository)
    }
}