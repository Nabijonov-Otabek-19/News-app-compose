package uz.gita.newsappcompose.domain.repository

import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import uz.gita.newsappcompose.data.response.ErrorResponse
import uz.gita.newsappcompose.data.response.NewsData
import uz.gita.newsappcompose.data.source.remote.Api
import uz.gita.newsappcompose.utils.API_KEY
import uz.gita.newsappcompose.utils.COUNTRY
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val api: Api
) : NewsRepository {

    override fun loadLatestNews(): Flow<Result<NewsData>> = callbackFlow {

        val response = api.getLatestNews(API_KEY, COUNTRY)

        when (response.code()) {
            in 200..300 -> {
                trySend(Result.success(response.body() as NewsData))
            }

            else -> {
                trySend(Result.failure(Exception((response.errorBody() as ErrorResponse).message)))
            }
        }
        awaitClose()
    }
}