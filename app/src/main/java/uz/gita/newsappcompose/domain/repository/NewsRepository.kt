package uz.gita.newsappcompose.domain.repository

import kotlinx.coroutines.flow.Flow
import uz.gita.newsappcompose.data.response.NewsData
import uz.gita.newsappcompose.data.response.ResultData

interface NewsRepository {
    fun loadLatestNews(): Flow<Result<NewsData>>

    fun addToSaved(resultData: ResultData)
    fun deleteFromSaved(resultData: ResultData)

    fun getSavedNews(): Flow<List<ResultData>>
    fun checkSavedNews(title: String): Boolean
}