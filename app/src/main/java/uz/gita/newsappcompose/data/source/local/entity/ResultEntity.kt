package uz.gita.newsappcompose.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import uz.gita.newsappcompose.data.response.Result

@Entity(tableName = "news")
data class ResultEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,
    val content: String,
    val description: String,
    val image_url: String? = null,
    val language: String,
    val link: String,
    val pubDate: String,
    val source_id: String,
    val title: String
) {
    fun toData() = Result(
        id = id,
        content = content, description = description,
        image_url = image_url ?: "", language = language,
        link = link, pubDate = pubDate, source_id = source_id,
        title = title, category = listOf(),
        country = listOf(), creator = listOf(),
        keywords = listOf(), video_url = ""
    )
}