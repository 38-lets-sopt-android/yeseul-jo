package com.example.letssopt.presentation.login

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.R
import com.example.letssopt.core.data.DataStore
import com.example.letssopt.core.designsystem.component.LabeledTextField
import com.example.letssopt.core.designsystem.component.PrimaryButton
import com.example.letssopt.core.designsystem.theme.Background
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.core.designsystem.theme.PrimaryRed
import com.example.letssopt.core.designsystem.theme.TextPrimary
import com.example.letssopt.core.designsystem.theme.TextSecondary

@Composable
fun LoginScreen(
    onSignupClick: () -> Unit, // 회원가입 버튼 클릭 시
    onLoginSuccess: () -> Unit, // 로그인 성공 시
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = viewModel(),
) {
    val context = LocalContext.current
    val state = viewModel.state

    LaunchedEffect(state) {
        if (state.isLoginSuccess) {
            onLoginSuccess()
            Toast.makeText(context, "로그인 성공!", Toast.LENGTH_SHORT).show()
        }
        state.errorMessage?.let { message ->
            Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
            viewModel.clearErrorMessage()
        }
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .systemBarsPadding()
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
            text = stringResource(R.string.login_title),
            color = TextPrimary,
            style = typography.headlineMedium,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(36.dp))

        // input form
        LabeledTextField(
            label = stringResource(R.string.email),
            value = viewModel.email,
            onValueChange = { viewModel.updateEmail(it) },
            placeholder = stringResource(R.string.email_placeholder)
        )
        Spacer(modifier = Modifier.height(18.dp))
        LabeledTextField(
            label = stringResource(R.string.password),
            value = viewModel.password,
            onValueChange = { viewModel.updatePassword(it) },
            placeholder = stringResource(R.string.password_placeholder),
            isPassword = true
        )

        Spacer(modifier = Modifier.weight(1f))

        // bottom
        Text(
            text = stringResource(R.string.signup_question),
            color = TextSecondary,
            style = typography.labelSmall,
            modifier = Modifier.clickable { onSignupClick() }
        )
        Spacer(modifier = Modifier.height(20.dp))
        PrimaryButton(
            text = stringResource(R.string.login),
            onClick = {
                val savedEmail = DataStore.getSavedEmail(context)
                val savedPassword = DataStore.getSavedPassword(context)
                viewModel.login(savedEmail, savedPassword)
            },
            enabled = viewModel.isLoginEnabled
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun LoginPreview() {
    LETSSOPTTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Background
        ) {
            LoginScreen(
                onSignupClick = {},
                onLoginSuccess = {}
            )
        }
    }
}