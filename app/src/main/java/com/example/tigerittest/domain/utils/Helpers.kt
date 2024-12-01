package com.example.tigerittest.domain.utils

import androidx.compose.runtime.staticCompositionLocalOf
import androidx.navigation.NavController
import com.example.tigerittest.ui.viewmodels.PostsViewmodel


val LocalNavController = staticCompositionLocalOf<NavController> {
    error("NavController not provided")
}
val LocalPostsViewmodel = staticCompositionLocalOf<PostsViewmodel> {
    error("PostsViewmodel not provided")
}


const val USERS_SYNC_WORKER = "UserWorker"

const val POSTS_REMOTE_ID = "posts"
const val USERS_REMOTE_ID = "users"
