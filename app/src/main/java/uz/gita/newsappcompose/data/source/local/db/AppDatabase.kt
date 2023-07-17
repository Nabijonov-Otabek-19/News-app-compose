package uz.gita.newsappcompose.data.source.local.db

import androidx.room.Database
import androidx.room.RoomDatabase
import uz.gita.newsappcompose.data.source.local.dao.NewsDao
import uz.gita.newsappcompose.data.source.local.entity.ResultEntity

@Database(
    entities = [ResultEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getNewsDao(): NewsDao

}