package com.example.tigerittest.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.paging.LoadState
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemKey
import com.example.tigerittest.domain.utils.LocalPostsViewmodel
import com.example.tigerittest.ui.components.PostItem


@Composable
fun PostListScreen() {
    val viewModel = LocalPostsViewmodel.current
    // val postsList = viewModel.postsList.collectAsState()
    val posts = viewModel.posts.collectAsLazyPagingItems()

    LaunchedEffect(null) {
        //viewModel.getPostsFromApi()
    }
    if (posts.loadState.refresh is LoadState.Loading) {
        Box(modifier = Modifier.fillMaxSize()) {
            CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
        }
    } else {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(
                count = posts.itemCount,
                key = posts.itemKey { it },
            ) { index ->
                val postItem = posts[index]

                if (postItem != null) {
                    PostItem(postItem = postItem, onItemClicked = {})
                    Spacer(modifier = Modifier.height(10.dp))
                }

            }
        }
    }

}