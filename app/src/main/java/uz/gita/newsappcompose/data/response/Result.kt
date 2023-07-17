package uz.gita.newsappcompose.data.response

import uz.gita.newsappcompose.data.source.local.entity.ResultEntity

data class Result(
    val id : Int = 0,
    val category: List<String>,
    val content: String,
    val country: List<String>,
    val creator: List<String>,
    val description: String,
    val image_url: String?,
    val keywords: List<String>,
    val language: String,
    val link: String,
    val pubDate: String,
    val source_id: String,
    val title: String,
    val video_url: Any
) {
    fun toEntity() = ResultEntity(
        id = id,
        content = content, description = description, image_url = image_url ?: "",
        language = language, link = link, pubDate = pubDate, source_id = source_id, title = title
    )
}