package uz.gita.newsappcompose.data.response

data class Result(
    val category: List<String>,
    val content: String,
    val country: List<String>,
    val creator: List<String>,
    val description: String,
    val image_url: String,
    val keywords: List<String>,
    val language: String,
    val link: String,
    val pubDate: String,
    val source_id: String,
    val title: String,
    val video_url: Any
)