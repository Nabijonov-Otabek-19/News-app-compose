package uz.gita.newsappcompose.domain.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import uz.gita.newsappcompose.data.response.NewsData
import uz.gita.newsappcompose.data.response.ResultData
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

    override fun addToSaved(resultData: ResultData) {
        dao.add(resultData.toEntity())
    }

    override fun deleteFromSaved(resultData: ResultData) {
        dao.delete(resultData.toEntity())
    }

    override fun getSavedNews(): Flow<List<ResultData>> =
        dao.getSavedNews().map { list ->
            list.map { data -> data.toData() }
        }

    override fun checkSavedNews(title: String): Boolean {
        return dao.checkSavedNews(title)
    }
}