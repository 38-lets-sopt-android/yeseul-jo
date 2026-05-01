package com.example.letssopt.presentation.signup

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.R
import com.example.letssopt.core.designsystem.component.LabeledTextField
import com.example.letssopt.core.designsystem.component.PrimaryButton
import com.example.letssopt.core.designsystem.theme.Background
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.core.designsystem.theme.PrimaryRed
import com.example.letssopt.core.designsystem.theme.TextPrimary

@Composable
fun SignupScreen(
    onSignupSuccess: (String, String) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SignupViewModel = viewModel()
) {
    val context = LocalContext.current

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(
                start = 20.dp,
                top = 60.dp,
                end = 20.dp,
                bottom = 26.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = stringResource(R.string.logo),
            color = PrimaryRed,
            style = typography.displayLarge,
        )

        Spacer(modifier = Modifier.height(26.dp))

        Text(
            text = stringResource(R.string.signup),
            color = TextPrimary,
            style = typography.headlineMedium,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(36.dp))

        // input form
        Column(
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            LabeledTextField(
                label = stringResource(R.string.email),
                value = viewModel.email,
                onValueChange = { viewModel.updateEmail(it) },
                placeholder = stringResource(R.string.email_placeholder)
            )
            LabeledTextField(
                label = stringResource(R.string.password),
                value = viewModel.password,
                onValueChange = { viewModel.updatePassword(it) },
                placeholder = stringResource(R.string.password_placeholder),
                isPassword = true
            )
            LabeledTextField(
                label = stringResource(R.string.password_confirm),
                value = viewModel.passwordConfirm,
                onValueChange = { viewModel.updatePasswordConfirm(it) },
                placeholder = stringResource(R.string.password_confirm_placeholder),
                isPassword = true
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // bottom
        PrimaryButton(
            text = stringResource(R.string.signup),
            onClick = {
                when {
                    !viewModel.isEmailValid -> {
                        Toast.makeText(
                            context,
                            R.string.signup_email_toast,
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    !viewModel.isPasswordValid -> {
                        Toast.makeText(
                            context,
                            R.string.signup_password_toast,
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    !viewModel.isPasswordConfirmed -> {
                        Toast.makeText(
                            context,
                            R.string.signup_password_confirmed_toast,
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    else -> {
                        onSignupSuccess(viewModel.email, viewModel.password)
                    }
                }
            },
            enabled = viewModel.isAllInputFilled
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun SignupPreview() {
    LETSSOPTTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Background
        ) {
            SignupScreen(
                onSignupSuccess = { _, _ -> }
            )
        }
    }
}