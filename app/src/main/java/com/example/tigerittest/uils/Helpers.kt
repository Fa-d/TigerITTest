package com.example.tigerittest.uils

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController
import com.example.tigerittest.ui.viewmodels.PostsViewmodel


val LocalNavController = staticCompositionLocalOf<NavController> {
    error("NavController not provided")
}
val LocalPostsViewmodel = staticCompositionLocalOf<PostsViewmodel> {
    error("PostsViewmodel not provided")
}

