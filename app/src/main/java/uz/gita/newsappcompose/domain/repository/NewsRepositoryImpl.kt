package uz.gita.newsappcompose.domain.repository

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import uz.gita.newsappcompose.data.response.NewsData
import uz.gita.newsappcompose.data.source.remote.Api
import uz.gita.newsappcompose.utils.API_KEY
import uz.gita.newsappcompose.utils.COUNTRY
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: Api
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
}