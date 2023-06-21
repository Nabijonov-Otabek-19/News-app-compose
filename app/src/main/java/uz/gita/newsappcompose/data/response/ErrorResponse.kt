package uz.gita.newsappcompose.data.response

data class ErrorResponse(
    val code: String,
    val message: String,
    val status: String
)