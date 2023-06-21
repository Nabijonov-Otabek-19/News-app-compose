package uz.gita.newsappcompose.di

import android.content.Context
import com.chuckerteam.chucker.api.ChuckerInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import uz.gita.newsappcompose.data.source.remote.Api
import uz.gita.newsappcompose.utils.BASE_URL
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class NetworkModule {

    @Provides
    @Singleton
    fun provideHttpClient(@ApplicationContext context: Context): OkHttpClient =
        OkHttpClient
            .Builder()
            .addInterceptor(
                ChuckerInterceptor
                    .Builder(context)
                    .build()
            ).build()

    @Provides
    @Singleton
    fun provideRetrofit2(okHttpClient: OkHttpClient): Retrofit =
        Retrofit
            .Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(
                GsonConverterFactory
                    .create()
            )
            .client(okHttpClient)
            .build()

    @Provides
    @Singleton
    fun provideApi(retrofit: Retrofit): Api = retrofit.create(Api::class.java)
}