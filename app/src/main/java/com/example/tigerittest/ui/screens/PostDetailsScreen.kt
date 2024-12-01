package com.example.tigerittest.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.tigerittest.domain.utils.LocalNavController
import com.example.tigerittest.ui.nav.NavScreens
import com.example.tigerittest.ui.viewmodels.PostsViewmodel


@Composable
fun PostDetailsScreen() {
    val viewModel = hiltViewModel<PostsViewmodel>()
    val currentNav = LocalNavController.current

    val postDetails = viewModel.postDetails.collectAsState().value
    LaunchedEffect(null) {
        viewModel.getPostDetailsWithUserData()
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .fillMaxHeight(), verticalArrangement = Arrangement.Center
    ) {
        Text(
            postDetails.name,
            modifier = Modifier
                .padding(vertical = 10.dp, horizontal = 10.dp)
                .clickable {
                    currentNav.navigate(NavScreens.USER_DETAIL_SCREEN + "/${postDetails.userId}")
                },
            style = MaterialTheme.typography.displayMedium
        )
        Text(
            postDetails.postTitle,
            modifier = Modifier.padding(vertical = 5.dp, horizontal = 10.dp),
            style = MaterialTheme.typography.titleMedium
        )

        Spacer(Modifier.height(40.dp))

        Text(
            postDetails.postContent,
            modifier = Modifier.padding(vertical = 10.dp, horizontal = 10.dp),
            style = MaterialTheme.typography.bodySmall
        )

    }

}