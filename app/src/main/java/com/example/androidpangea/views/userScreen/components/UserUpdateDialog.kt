package com.example.androidpangea.views.userScreen.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.androidpangea.R
import com.example.androidpangea.views.subviews.EmailTextField

@Composable
fun UserUpdateDialog(
    showDialog: MutableState<Boolean> = mutableStateOf(false),
    email: TextFieldValue = TextFieldValue(stringResource(id = R.string.preview_email)),
    onEmailValueChange: (email: TextFieldValue) -> Unit = {},
    username: TextFieldValue = TextFieldValue(stringResource(id = R.string.preview_name)),
    onUsernameValueChange: (displayName: TextFieldValue) -> Unit = {},
    onClickConfirm: () -> Unit = {}
) {
    val focusManager = LocalFocusManager.current
    var isButtonEnabled by remember {
        mutableStateOf(true)
    }

    AlertDialog(
        modifier = Modifier,
        onDismissRequest = { showDialog.value = false },
        title = {
            Text(
                text = stringResource(id = R.string.profile_information),
                style = MaterialTheme.typography.titleMedium
            )
        },
        text = {
            Column(
                modifier = Modifier.padding(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                EmailTextField(
                    email = email,
                    onEmailValueChange = { email, isError ->
                        onEmailValueChange(email)
                        isButtonEnabled = isError
                    },
                    keyboardActions = KeyboardActions(
                        onNext = { focusManager.moveFocus(FocusDirection.Down) }
                    ),
                )
                OutlinedTextField(
                    modifier = Modifier,
                    value = username,
                    onValueChange = onUsernameValueChange,
                    label = {
                        Text(
                            text = stringResource(id = R.string.username_label),
                            style = MaterialTheme.typography.bodySmall
                        )
                    },
                )
                Spacer(modifier = Modifier.height(8.dp))


            }
        },
        confirmButton = {
            Button(
                modifier = Modifier,
                onClick = onClickConfirm,
                enabled = isButtonEnabled,
            ) {
                Text(
                    text = stringResource(id = R.string.save),
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        },
    )
}