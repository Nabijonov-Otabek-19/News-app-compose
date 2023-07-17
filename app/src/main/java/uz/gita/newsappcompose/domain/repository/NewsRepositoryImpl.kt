package uz.gita.newsappcompose.domain.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import uz.gita.newsappcompose.data.response.NewsData
import uz.gita.newsappcompose.data.response.Result as Result1
import uz.gita.newsappcompose.data.source.local.dao.NewsDao
import uz.gita.newsappcompose.data.source.remote.Api
import uz.gita.newsappcompose.utils.API_KEY
import uz.gita.newsappcompose.utils.COUNTRY
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: Api,
    private val dao: NewsDao
) : NewsRepository {

    override fun loadLatestNews(): Flow<Result<NewsData>> = flow {

        val response = api.getLatestNews(API_KEY, COUNTRY)

        when (response.code()) {
            in 200..299 -> {
                emit(Result.success(response.body() as NewsData))
            }

            else -> {
                emit(Result.failure(Exception(response.errorBody()?.string())))
            }
        }
    }
        .catch { emit(Result.failure(it)) }
        .flowOn(Dispatchers.IO)

    override fun addToSaved(result: Result1) {
        dao.add(result.toEntity())
    }

    override fun deleteFromSaved(result: Result1) {
        dao.delete(result.toEntity())
    }

    override fun getSavedNews(): Flow<List<Result1>> =
        dao.getSavedNews().map { list ->
            list.map { data -> data.toData() }
        }

    override fun checkSavedNews(title: String): Boolean {
        return dao.checkSavedNews(title)
    }
}