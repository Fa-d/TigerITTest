package com.example.tigerittest.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tigerittest.data.models.Post


@Composable
@Preview(showBackground = true)
fun PostItem(
    postItem: Post = Post(
        title = "Post title",
        body = "this is the post body describing the post with the post title and the post body and the post body contains a large string",
    ), onItemClicked: () -> Unit = {}
) {
    Card(onClick = { onItemClicked() }) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 10.dp),
            text = postItem.title,
            style = MaterialTheme.typography.titleMedium
        )
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .heightIn(max = 100.dp)
                .padding(horizontal = 10.dp, vertical = 10.dp),
            text = postItem.body,
            style = MaterialTheme.typography.bodyMedium
        )
    }
}