package com.example.androidpangea.views.userScreen

import android.annotation.SuppressLint
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.example.androidpangea.models.User

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun UserScreen(
    user: User,
//    userViewModel: UserViewModel,
//    navController: NavController,
) {
Scaffold {
    Text("USER")
}
}