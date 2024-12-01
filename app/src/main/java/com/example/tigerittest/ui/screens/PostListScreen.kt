package com.example.tigerittest.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.tigerittest.ui.components.PostItem
import com.example.tigerittest.uils.LocalPostsViewmodel


@Composable
fun PostListScreen() {
    val viewModel = LocalPostsViewmodel.current
    val postsList = viewModel.postsList.collectAsState()
    LaunchedEffect(null) {
        viewModel.getPosts()
    }
    if (postsList.value.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(postsList.value.size) { index ->
                val postItem = postsList.value[index]
                PostItem(postItem = postItem, onItemClicked = {})
                Spacer(modifier = Modifier.height(10.dp))


            }
        }
    }

}