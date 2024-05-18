package com.example.androidpangea.views.userScreen.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccessTime
import androidx.compose.material.icons.outlined.Call
import androidx.compose.material.icons.outlined.Email
import androidx.compose.material.icons.outlined.Person
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.androidpangea.R
import com.example.androidpangea.models.User

@Composable
fun Fields(user: User?) {
    Column(
        modifier = Modifier
            .padding(4.dp),
        horizontalAlignment = Alignment.Start,
        verticalArrangement = Arrangement.spacedBy(4.dp)
    ) {
        FieldRow(
            content = user?.username,
            imageVector = Icons.Outlined.Person,
            contentDescription = stringResource(id = R.string.username_content_description),
        )
        FieldRow(
            content = user?.email,
            imageVector = Icons.Outlined.Email,
            contentDescription = stringResource(id = R.string.email_content_description),
        )
       FieldRow(
            content = user?.location,
            imageVector = Icons.Outlined.Call,
            contentDescription = stringResource(id = R.string.location_content_description),
        )
      FieldRow(
            content = user?.createdAt,
            imageVector = Icons.Outlined.AccessTime,
            contentDescription = stringResource(id = R.string.created_at_content_description),
        )
    }
}