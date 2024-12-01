package com.example.tigerittest.ui.screens

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import com.example.tigerittest.uils.LocalPostsViewmodel


@Composable
fun PostListScreen() {

    val viewModel = LocalPostsViewmodel.current

    LaunchedEffect(null) {
        viewModel.getPosts()
    }

    Text("PostListScreen")
}