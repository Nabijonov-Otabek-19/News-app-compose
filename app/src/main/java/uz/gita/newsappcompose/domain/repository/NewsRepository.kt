package uz.gita.newsappcompose.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.newsappcompose.data.response.NewsData

interface NewsRepository {
    fun loadLatestNews(): Flow<Result<NewsData>>
}