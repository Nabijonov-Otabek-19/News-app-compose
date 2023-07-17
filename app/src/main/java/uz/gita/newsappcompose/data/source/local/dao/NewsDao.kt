package uz.gita.newsappcompose.data.source.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import uz.gita.newsappcompose.data.source.local.entity.ResultEntity

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun add(resultEntity: ResultEntity)

    @Delete
    fun delete(resultEntity: ResultEntity)

    @Query("Select * from news")
    fun getSavedNews(): Flow<List<ResultEntity>>

    @Query("Select exists (Select * from news Where title =:title)")
    fun checkSavedNews(title: String): Boolean
}