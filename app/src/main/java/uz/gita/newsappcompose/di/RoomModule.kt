package uz.gita.newsappcompose.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import uz.gita.newsappcompose.data.source.local.dao.NewsDao
import uz.gita.newsappcompose.data.source.local.db.AppDatabase
import javax.inject.Singleton


@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @[Provides Singleton]
    fun provideDB(@ApplicationContext context: Context): AppDatabase = Room.databaseBuilder(
        context,
        AppDatabase::class.java, "News"
    ).allowMainThreadQueries().build()

    @[Provides Singleton]
    fun provideNewsDao(db: AppDatabase): NewsDao = db.getNewsDao()
}