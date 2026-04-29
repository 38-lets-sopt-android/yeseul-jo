package com.example.letssopt.screen

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
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
import com.example.letssopt.data.viewmodel.SignupViewModel
import com.example.letssopt.designsystem.component.LabeledTextField
import com.example.letssopt.designsystem.component.PrimaryButton
import com.example.letssopt.designsystem.theme.Background
import com.example.letssopt.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.designsystem.theme.PrimaryRed
import com.example.letssopt.designsystem.theme.TextPrimary

class SignupActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LETSSOPTTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    SignupScreen(
                        modifier = Modifier.padding(innerPadding),
                        onSignupSuccess = { email, password ->
                            // 조건을 만족할 때 데이터 전달
                            val intent = Intent().apply {
                                putExtra("email", email.trim())
                                putExtra("password", password.trim())
                            }
                            setResult(RESULT_OK, intent)
                            finish()
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun SignupScreen(
    modifier: Modifier = Modifier,
    viewModel: SignupViewModel = viewModel(),
    onSignupSuccess: (String, String) -> Unit
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
                onValueChange = { viewModel.email = it },
                placeholder = stringResource(R.string.email_placeholder)
            )
            LabeledTextField(
                label = stringResource(R.string.password),
                value = viewModel.password,
                onValueChange = { viewModel.password = it },
                placeholder = stringResource(R.string.password_placeholder),
                isPassword = true
            )
            LabeledTextField(
                label = stringResource(R.string.password_confirm),
                value = viewModel.passwordConfirm,
                onValueChange = { viewModel.passwordConfirm = it },
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