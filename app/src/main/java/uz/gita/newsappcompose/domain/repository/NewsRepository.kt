package uz.gita.newsappcompose.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.newsappcompose.data.response.NewsData
import uz.gita.newsappcompose.data.response.Result

interface NewsRepository {
    fun loadLatestNews(): Flow<kotlin.Result<NewsData>>

    fun addToSaved(result: Result)
    fun deleteFromSaved(result: Result)

    fun getSavedNews(): Flow<List<Result>>
    fun checkSavedNews(title: String): Boolean
}