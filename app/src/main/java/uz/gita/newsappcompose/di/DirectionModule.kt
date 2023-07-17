package uz.gita.newsappcompose.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import uz.gita.newsappcompose.presentation.screen.home.page.home.HomeContract
import uz.gita.newsappcompose.presentation.screen.home.page.home.HomeDirection
import uz.gita.newsappcompose.presentation.screen.read.ReadContract
import uz.gita.newsappcompose.presentation.screen.read.ReadDirection
import javax.inject.Singleton


@Module
@InstallIn(ViewModelComponent::class)
interface DirectionModule {

    @[Binds Singleton]
    fun bindHomeScreenDirection(impl: HomeDirection): HomeContract.Direction

    @[Binds Singleton]
    fun bindReadScreenDirection(impl: ReadDirection): ReadContract.Direction
}