package com.example.letssopt.presentation.signup

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
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
    val state = viewModel.state

    LaunchedEffect(state) {
        if (state.isSignupSuccess) {
            onSignupSuccess(viewModel.email, viewModel.password)
        }
        state.errorMessageRes?.let { resId ->
            Toast.makeText(context, resId, Toast.LENGTH_SHORT).show()
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
            text = stringResource(R.string.signup),
            color = TextPrimary,
            style = typography.headlineMedium,
            modifier = Modifier.align(Alignment.Start)
        )

        Spacer(modifier = Modifier.height(36.dp))

        // input form
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            LabeledTextField(
                label = stringResource(R.string.login_id),
                value = viewModel.id,
                onValueChange = { viewModel.updateId(it) },
                placeholder = stringResource(R.string.login_id_placeholder)
            )
            LabeledTextField(
                label = stringResource(R.string.login_password),
                value = viewModel.password,
                onValueChange = { viewModel.updatePassword(it) },
                placeholder = stringResource(R.string.login_password_placeholder),
                isPassword = true
            )
            LabeledTextField(
                label = stringResource(R.string.signup_password_confirm),
                value = viewModel.passwordConfirm,
                onValueChange = { viewModel.updatePasswordConfirm(it) },
                placeholder = stringResource(R.string.signup_password_confirm_placeholder),
                isPassword = true
            )
            LabeledTextField(
                label = stringResource(R.string.signup_name),
                value = viewModel.name,
                onValueChange = { viewModel.updateName(it) },
                placeholder = stringResource(R.string.signup_name_placeholder)
            )
            LabeledTextField(
                label = stringResource(R.string.signup_email),
                value = viewModel.email,
                onValueChange = { viewModel.updateEmail(it) },
                placeholder = stringResource(R.string.signup_email_placeholder)
            )
            LabeledTextField(
                label = stringResource(R.string.signup_age),
                value = viewModel.age,
                onValueChange = { viewModel.updateAge(it) },
                placeholder = stringResource(R.string.signup_age_placeholder)
            )
            LabeledTextField(
                label = stringResource(R.string.signup_part),
                value = viewModel.part,
                onValueChange = { viewModel.updatePart(it) },
                placeholder = stringResource(R.string.signup_part_placeholder)
            )

            Spacer(modifier = Modifier.height(28.dp))

        }

        Spacer(modifier = Modifier.height(20.dp))

        // bottom
        PrimaryButton(
            text = stringResource(R.string.signup),
            onClick = {
                viewModel.signup()
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