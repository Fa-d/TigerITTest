package com.example.tigerittest.ui.nav

import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tigerittest.ui.screens.PostDetailsScreen
import com.example.tigerittest.ui.screens.PostListScreen
import com.example.tigerittest.ui.screens.UserDetailScreen
import com.example.tigerittest.domain.utils.LocalNavController

@Composable
fun DummyPostNavGraph(
    navController: NavHostController, startDestination: String = NavScreens.POSTS_SCREEN
) {
    val currentNav = LocalNavController.current

    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = NavScreens.POSTS_SCREEN) {
            PostListScreen()
            BackHandler {
                (currentNav.context as ComponentActivity).finish()
            }
        }
        composable(route = NavScreens.POST_DETAIL_SCREEN) {
            PostDetailsScreen()
        }
        composable(route = NavScreens.USER_DETAIL_SCREEN) {
            UserDetailScreen()
        }
    }

}