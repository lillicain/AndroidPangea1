package com.example.androidpangea.views.postScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.androidpangea.R
import com.example.androidpangea.models.Post
import com.example.androidpangea.views.subviews.DetailText

@Composable
fun PostCard(
    post: Post,
    deleteItem: () -> Unit,
    navigate: () -> Unit
) {
    Card(modifier = Modifier.fillMaxWidth().padding(10.dp),
        shape = MaterialTheme.shapes.medium,
        elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
        onClick = { navigate() }) {
        Row(
            modifier = Modifier.fillMaxWidth().padding(8.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(
                modifier = Modifier.padding(8.dp)
            ) {
                DetailText(details = post.user, fontSize = 36)
                DetailText(details = post.location.toString())
                DetailText(details = post.description)
                DetailText(details = post.post)
                post.post.let {
                    DetailText(details = it)
                }
            }



            Spacer(modifier = Modifier.weight(1f))

            IconButton(onClick = { deleteItem() }) {
                Icon(
                    imageVector = Icons.Outlined.Delete, contentDescription = "Delete"
                )
            }
        }
    }
}



