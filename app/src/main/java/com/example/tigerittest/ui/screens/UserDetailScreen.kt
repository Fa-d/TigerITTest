package com.example.tigerittest.ui.screens

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tigerittest.ui.components.PostItem
import com.example.tigerittest.ui.viewmodels.PostsViewmodel

@Composable
fun UserDetailScreen() {
    val viewModel = hiltViewModel<PostsViewmodel>()
    val postList = viewModel.postsByUser.collectAsState()
    LaunchedEffect(null) { viewModel.getAllPostsOfAUser() }

    Scaffold() { padding ->
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(padding),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            items(postList.value.size) { index ->
                val postItem = postList.value[index]
                PostItem(postItem = postItem, onItemClicked = {})
                Spacer(modifier = Modifier.height(10.dp))
            }
        }
    }
}