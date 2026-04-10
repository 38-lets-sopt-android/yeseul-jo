package com.example.letssopt.screen

import android.os.Bundle
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
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
        mutableStateOf("")
    }
    var passwordConfirm by remember {
        mutableStateOf("")
    }

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
            onClick = {},
            enabled = email.isNotEmpty() && password.isNotEmpty() && passwordConfirm.isNotEmpty()
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