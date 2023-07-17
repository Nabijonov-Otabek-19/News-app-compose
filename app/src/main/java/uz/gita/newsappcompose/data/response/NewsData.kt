package uz.gita.newsappcompose.data.response

data class NewsData(
    val nextPage: String,
    val resultData: List<ResultData>,
    val status: String,
    val totalResults: Int
)