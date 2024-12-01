package com.example.tigerittest.ui.nav

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.tigerittest.uils.LocalNavController

@Composable
fun DummyPostNavGraph(
    navController: NavHostController, startDestination: String = NavScreens.POSTS_SCREEN
) {
    val currentNav = LocalNavController.current

    NavHost(navController = navController, startDestination = startDestination) {
        composable(route = NavScreens.POSTS_SCREEN) {

        }
        composable(route = NavScreens.POST_DETAIL_SCREEN) {

        }
        composable(route = NavScreens.USER_DETAIL_SCREEN) {

        }
    }

}