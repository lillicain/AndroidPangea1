package com.example.androidpangea.views.postScreen

import android.annotation.SuppressLint
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.tween
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridItemSpan
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SearchBar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.androidpangea.R
import com.example.androidpangea.extensions.BaseState
import com.example.androidpangea.models.Post
import com.example.androidpangea.navigation.NavigationItem
import com.example.androidpangea.views.mainScreen.MainViewModel
import com.example.androidpangea.views.subviews.ImagePicker
import retrofit2.Response

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun ExploreScreen(viewModel: MainViewModel, navController: NavController) {

    val postsState by viewModel.posts.collectAsState()



    Scaffold {


        //        ImagePicker()

        Box {
            var searchData by rememberSaveable {
                mutableStateOf(emptyList<Post>())
            }
            var query by rememberSaveable {
                mutableStateOf("")
            }
            LazyVerticalStaggeredGrid(
                columns = StaggeredGridCells.Adaptive(125.dp),
                modifier = Modifier.animateContentSize(animationSpec = tween(2000))
            ) {
                item(span = StaggeredGridItemSpan.FullLine) {
                    SearchBar(modifier = Modifier.fillMaxWidth()
                        .padding(vertical = 8.dp, horizontal = 16.dp),
                        query = query,
                        leadingIcon = {
                            Icon(Icons.Default.Search, contentDescription = "Search")
                        },
                        trailingIcon = {
                            Icon(Icons.Default.Close,
                                contentDescription = "Clear",
                                modifier = Modifier.clickable {
                                    query = ""
                                })
                        },
                        onQueryChange = { q ->
                            val d = searchData.filter {
                                it.description.lowercase().contains(q.lowercase())
                            }
                            searchData = d
                            query = q
                        },
                        placeholder = {
                            Text(
                                "Search", fontSize = 16.sp, color = Color.Gray
                            )
                        },
                        onSearch = {},
                        active = false,
                        onActiveChange = {}) {}
                }
                if (searchData.isEmpty() || query.isEmpty()) {
//                    searchData = state.data
                } else {
                    searchData.map { it.description.lowercase() }.contains(query.lowercase())
                }
//                items(searchData) {
//                    AsyncImage(it.postImage,
//                        contentDescription = it.description,
//                        contentScale = ContentScale.Fit,
//                        modifier = Modifier.border(
//                            1.dp, color = MaterialTheme.colorScheme.background
//                        ).clickable {
//                            navController.navigate(
//                                "${NavigationItem.User.route}/${it}"
//                            )
//                        })
//                }
            }
        }
    }
}