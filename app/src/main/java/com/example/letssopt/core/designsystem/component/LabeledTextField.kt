package com.example.letssopt.core.designsystem.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.R
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.core.designsystem.theme.Placeholder
import com.example.letssopt.core.designsystem.theme.PrimaryRed
import com.example.letssopt.core.designsystem.theme.Surface
import com.example.letssopt.core.designsystem.theme.TextPrimary
import com.example.letssopt.core.designsystem.theme.TextSecondary

@Composable
fun LabeledTextField(
    label: String,
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    modifier: Modifier = Modifier,
    isPassword: Boolean = false,
) {
    Column(
        modifier = modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(3.dp)
    ) {
        // label
        Text(
            text = label,
            style = typography.labelSmall,
            color = TextSecondary
        )
        // input
        BasicTextField(
            value = value,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            textStyle = typography.labelSmall.copy(color = TextPrimary),
            visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
            cursorBrush = SolidColor(PrimaryRed),
            decorationBox = { innerTextField ->
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Surface, RoundedCornerShape(8.dp))
                        .padding(horizontal = 16.dp, vertical = 18.dp),
                    contentAlignment = Alignment.CenterStart
                ) {
                    // 값이 없을 때에만 placeholder
                    if (value.isEmpty()) {
                        Text(
                            text = placeholder,
                            style = typography.labelSmall,
                            color = Placeholder
                        )
                    }
                    innerTextField()
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun TextFieldPreview() {
    LETSSOPTTheme {
        Column(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            LabeledTextField(
                label = stringResource(R.string.email),
                value = "",
                onValueChange = {},
                placeholder = stringResource(R.string.email_placeholder)
            )
            LabeledTextField(
                label = stringResource(R.string.email),
                value = "sopt@sopt.org",
                onValueChange = {},
                placeholder = stringResource(R.string.email_placeholder)
            )
        }
    }
}