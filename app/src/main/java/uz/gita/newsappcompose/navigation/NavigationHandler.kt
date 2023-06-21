package uz.gita.newsappcompose.navigation

import kotlinx.coroutines.flow.Flow

interface NavigationHandler {
    val navigationBuffer: Flow<NavigationArg>
}