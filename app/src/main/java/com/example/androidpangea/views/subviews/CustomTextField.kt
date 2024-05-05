package com.example.androidpangea.views.subviews

import androidx.annotation.StringRes
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.androidpangea.R
import com.example.androidpangea.ui.theme.MaterialColors.Gray

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    value: String,
    onValueChange: (String) -> Unit,
    keyboardType: KeyboardType = KeyboardType.Text,
    isPasswordTextField: Boolean = false,
    isSingleLine: Boolean = true,
    hint: String
) {

    var isPasswordVisible by remember {
        mutableStateOf(false)
    }

    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier.fillMaxWidth(),
        textStyle = MaterialTheme.typography.bodyMedium,
        keyboardOptions = KeyboardOptions.Default.copy(
            keyboardType = keyboardType
        ),
        singleLine = isSingleLine,
        colors = TextFieldDefaults.textFieldColors(
//            backgroundColor = if (isSystemInDarkTheme()) {
                MaterialTheme.colorScheme.surface,
//            } else {
//                Gray
//            },
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ),
        trailingIcon = if (isPasswordTextField){
            {
                PasswordEyeIcon(isPasswordVisible = isPasswordVisible) {
                    isPasswordVisible = !isPasswordVisible
                }
            }
        }else{
            null
        },
        visualTransformation = if (isPasswordTextField){
            if (isPasswordVisible){
                VisualTransformation.None
            }else{
                PasswordVisualTransformation()
            }
        }else{
            VisualTransformation.None
        },
        placeholder = {
            Text(text = "Sign In", style = MaterialTheme.typography.bodyMedium)
        },
        shape = MaterialTheme.shapes.medium
    )

}

@Composable
fun PasswordEyeIcon(
    isPasswordVisible: Boolean,
    onPasswordVisibilityToggle: () -> Unit
) {

    val image = if (isPasswordVisible){
        painterResource(id = R.drawable.show_eye_icon_filled)
    } else {
        painterResource(id = R.drawable.hide_eye_icon_filled)
    }

    IconButton(onClick = onPasswordVisibilityToggle) {
        Icon(painter = image, contentDescription = null)
    }
}