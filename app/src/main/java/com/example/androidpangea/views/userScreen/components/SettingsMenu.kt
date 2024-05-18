package com.example.androidpangea.views.userScreen.components

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import com.example.androidpangea.R

@Composable
fun SettingsMenu(
    modifier: Modifier = Modifier,
    signOut: () -> Unit = {},
    revokeAccess: () -> Unit = {},
    navigateToForgotPasswordScreen: (() -> Unit)? = null,
) {
    var openMenu by remember { mutableStateOf(false) }

    IconButton(
        modifier = modifier,
        onClick = { openMenu = !openMenu },
    ) {
        Icon(
            modifier = Modifier.size(50.dp),
            imageVector = Icons.Default.MoreVert,
            contentDescription = null
        )
        DropdownMenu(
            expanded = openMenu,
            offset = DpOffset(x = (6).dp, y = (-36).dp),
            onDismissRequest = { openMenu = !openMenu },
        ) {
            DropdownMenuItem(
                modifier = Modifier.testTag(stringResource(id = R.string.sign_out)),
                text = { Text(text = stringResource(id= R.string.sign_out)) },
                onClick = {
                    signOut()
                    openMenu = !openMenu
                },
            )
            DropdownMenuItem(
                modifier = Modifier
                    .testTag(stringResource(id = R.string.revoke_access)),
                text = { Text(text = stringResource(id= R.string.revoke_access)) },
                onClick = {
                    revokeAccess()
                    openMenu = !openMenu
                },
            )
            if (navigateToForgotPasswordScreen != null) {
                DropdownMenuItem(
                    modifier = Modifier.testTag(stringResource(id = R.string.forgot_password)),
                    text = { Text(text = stringResource(id= R.string.forgot_password)) },
                    onClick = {
                        navigateToForgotPasswordScreen()
                    },
                )
            }
        }
    }
}
