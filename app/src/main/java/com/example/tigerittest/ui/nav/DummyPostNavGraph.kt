package com.example.tigerittest.ui.nav

import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.tigerittest.domain.utils.LocalNavController
import com.example.tigerittest.ui.screens.PostDetailsScreen
import com.example.tigerittest.ui.screens.PostListScreen
import com.example.tigerittest.ui.screens.UserDetailScreen

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
        composable(
            NavScreens.POST_DETAIL_SCREEN + "/{userId}" + "/{postId}",
            arguments = listOf(
                navArgument("userId") { type = NavType.IntType },
                navArgument("postId") { type = NavType.IntType },
            ),
        ) {
            PostDetailsScreen()
        }
        composable(NavScreens.USER_DETAIL_SCREEN) {
            UserDetailScreen()
        }
    }

}