package uz.gita.newsappcompose.data.response

data class NewsData(
    val nextPage: String,
    val results: List<Result>,
    val status: String,
    val totalResults: Int
)