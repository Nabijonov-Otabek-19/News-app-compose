package uz.gita.newsappcompose.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import uz.gita.newsappcompose.domain.repository.NewsRepository
import uz.gita.newsappcompose.domain.repository.NewsRepositoryImpl

@Module
@InstallIn(SingletonComponent::class)
interface RepositoryModule {

    @Binds
    fun bindNewsRepository(impl: NewsRepositoryImpl): NewsRepository
}