package com.example.letssopt.screen

import android.app.Activity
import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.util.Patterns
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.R
import com.example.letssopt.designsystem.component.button.LabeledTextField
import com.example.letssopt.designsystem.component.button.PrimaryButton
import com.example.letssopt.designsystem.theme.Background
import com.example.letssopt.designsystem.theme.PrimaryRed
import com.example.letssopt.designsystem.theme.TextPrimary
import com.example.letssopt.designsystem.theme.LETSSOPTTheme

class SignupActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LETSSOPTTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Signup(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun Signup(modifier: Modifier = Modifier) {
    val context = LocalContext.current as Activity

    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var passwordConfirm by remember {
        mutableStateOf("")
    }

    // 모든 정보가 입력되었는지
    val isAllInputFilled =
        email.isNotEmpty() && password.isNotEmpty() && passwordConfirm.isNotEmpty()

    // 회원가입 성공 조건
    val isEmailValid = Patterns.EMAIL_ADDRESS.matcher(email).matches()
    val isPasswordValid = password.length in 8..12
    val isPasswordConfirmed = password == passwordConfirm

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
                value = email,
                onValueChange = { email = it },
                placeholder = stringResource(R.string.email_placeholder)
            )
            LabeledTextField(
                label = stringResource(R.string.password),
                value = password,
                onValueChange = { password = it },
                placeholder = stringResource(R.string.password_placeholder)
            )
            LabeledTextField(
                label = stringResource(R.string.password_confirm),
                value = passwordConfirm,
                onValueChange = { passwordConfirm = it },
                placeholder = stringResource(R.string.password_confirm_placeholder)
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // bottom
        PrimaryButton(
            text = stringResource(R.string.signup),
            onClick = {
                when {
                    !isEmailValid -> {
                        Toast.makeText(
                            context,
                            stringResource(R.string.signup_email_toast),
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    !isPasswordValid -> {
                        Toast.makeText(
                            context,
                            stringResource(R.string.signup_password_toast),
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    !isPasswordConfirmed -> {
                        Toast.makeText(
                            context,
                            stringResource(R.string.signup_password_confirmed_toast),
                            Toast.LENGTH_SHORT
                        ).show()
                    }

                    else -> {
                        // 조건을 만족할 때 데이터 전달
                        val intent = Intent().apply {
                            putExtra("email", email)
                            putExtra("password", password)
                        }
                        context.setResult(RESULT_OK, intent)
                        context.finish()
                    }
                }
            },
            enabled = isAllInputFilled
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SignupPreview() {
    LETSSOPTTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Background
        ) {
            Signup()
        }
    }
}