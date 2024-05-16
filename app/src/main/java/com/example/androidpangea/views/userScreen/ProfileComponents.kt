package com.example.androidpangea.views.userScreen

import android.content.res.Configuration
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.androidpangea.R
import com.example.androidpangea.models.Post
import com.example.androidpangea.models.User
import com.example.androidpangea.navigation.NavigationItem
import com.example.androidpangea.views.subviews.CircularImage
import com.example.androidpangea.views.subviews.openTab

@Composable
fun MiddlePart(
    user: User,
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    Column(modifier = modifier) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            user.profileImage?.let {
                CircularImage(
                    imageUrl = it, imageSize = 80.dp
                )
            }
            StateInfo(modifier = Modifier.weight(7f), user, navController)
        }
    }
}

@Composable
fun StateInfo(modifier: Modifier, user: User, navController: NavController) {
//    StateItem("Posts", user.postIds.size.toString(), modifier)
//    StateItem("Followers", user.followerIds.size.toString(), modifier.clickable {
//        navController.navigate(
//            "${NavigationItem.Followers.route}/false/${user.id}"
//        )
//    })
//    StateItem("Following", user.followingIds.size.toString(), modifier.clickable {
//        navController.navigate(
//            "${NavigationItem.Followers.route}/true/${user.id}"
//        )
//    })
}

@Composable
fun StateItem(
    desc: String,
    count: String,
    modifier: Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround,
        modifier = modifier
    ) {
        Text(
            text = count,
            fontSize = 18.sp,
            fontWeight = FontWeight.SemiBold,
            fontFamily = FontFamily.SansSerif
        )
        Text(
            text = desc,
            fontSize = 14.sp,
            fontFamily = FontFamily.SansSerif
        )
    }
}

@Composable
fun ProfileDescription(
    displayName: String,
    description: String,
    url: String?,
    followedBy: List<String>,
    otherCount: Int,
    modifier: Modifier = Modifier,
) {
    val letterSpacing = 0.5.sp
    val lineHeight = 16.sp
    val context = LocalContext.current
    Column(
        modifier = modifier
    ) {
        Text(
            text = displayName,
            fontWeight = FontWeight.SemiBold,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )
        Text(
            text = description,
            letterSpacing = letterSpacing,
            lineHeight = lineHeight
        )
        if (url != null) {
            Text(
                text = url,
                fontWeight = FontWeight.Normal,
                color = Color.Gray,
                letterSpacing = letterSpacing,
                lineHeight = lineHeight,
                fontSize = 14.sp,
                modifier = Modifier.clickable {
                    openTab(context, url)
                }
            )
        }
        if (followedBy.isNotEmpty()) {
            Text(text = buildAnnotatedString {
                val boldStyle = SpanStyle(
                    color = MaterialTheme.colorScheme.onBackground,
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 16.sp
                )
                append("Followed by ")
                followedBy.forEachIndexed { index, name ->
                    pushStyle(boldStyle)
                    append(name)
                    pop()
                    if (index < followedBy.size - 1) {
                        append(", ")
                    }
                    if (otherCount > 2) {
                        append(" and ")
                        pushStyle(boldStyle)
                        append("$otherCount others")
                    }
                }
            })
        }
    }
}

@Composable
fun ActionButton(
    modifier: Modifier = Modifier,
    text: String? = null,
    backgroundColor: Color = MaterialTheme.colorScheme.inverseOnSurface,
    icon: ImageVector? = null,
    textColor: Color = MaterialTheme.colorScheme.onBackground,
) {
    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = modifier
            .clip(RoundedCornerShape(6.dp))
            .background(backgroundColor)
            .padding(4.dp)
    ) {
        if (text != null) {
            Text(
                text = text,
                fontWeight = FontWeight.SemiBold,
                fontSize = 14.sp,
                color = textColor
            )
        }
        if (icon != null) {
            Icon(
                imageVector = icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

@Composable
fun PostTabView(
    modifier: Modifier = Modifier,
    imageWithText: List<Pair<String, Painter>>,
    onTabSelected: (selectedIndex: Int) -> Unit,
) {
    var selectedTabIndex by remember {
        mutableIntStateOf(0)
    }

    val inactiveColor = Color.Gray
    TabRow(
        selectedTabIndex = selectedTabIndex,
        containerColor = Color.Transparent,
        contentColor = Color.Black,
        modifier = modifier.padding(vertical = 1.dp)
    ) {
        imageWithText.forEachIndexed { index, item ->
            Tab(selected = selectedTabIndex == index,
                selectedContentColor = MaterialTheme.colorScheme.onBackground,
                unselectedContentColor = inactiveColor,
                onClick = {
                    selectedTabIndex = index
                    onTabSelected(index)
                }
            ) {
                Icon(
                    painter = item.second,
                    contentDescription = item.first,
                    tint = if (selectedTabIndex == index)
                        MaterialTheme.colorScheme.onBackground
                    else
                        inactiveColor,
                    modifier = Modifier
                        .padding(10.dp)
                        .size(20.dp)
                )
            }
        }
    }
}


@ExperimentalFoundationApi
@Composable
fun PostSection(
    posts: List<Post>,
    modifier: Modifier = Modifier,
    navController: NavController,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        modifier = modifier
            .scale(1.01f)
    ) {
        items(posts.size) {
            AsyncImage(
                it,
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .aspectRatio(1f)
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.background
                    )
                    .clickable {
//                        navController.navigate(
//                            "${NavigationItem.ViewPost.route}/${it.dp}"
//                        )
                    }
            )
        }
    }
}
