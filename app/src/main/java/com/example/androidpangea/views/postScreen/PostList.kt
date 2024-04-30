package com.example.androidpangea.views.postScreen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.androidpangea.models.Post

@Composable
fun PostList(
    listOfItems: List<Post>,
    deleteItem: (Post) -> Unit,
    navigateToUpdate: (Post) -> Unit
) {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp)
            .padding(top = 70.dp),
        verticalArrangement = Arrangement.Top
    ) {
//        items(listOfItems) {
//            PostCard(post = it, deleteItem = { deleteItem(it) }) {
//                navigateToUpdate(it)
//            }
//        }
    }
}