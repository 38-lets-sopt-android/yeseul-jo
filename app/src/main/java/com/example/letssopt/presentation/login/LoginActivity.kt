package com.example.letssopt.presentation.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.clickable
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
import com.example.letssopt.core.designsystem.component.LabeledTextField
import com.example.letssopt.core.designsystem.component.PrimaryButton
import com.example.letssopt.core.designsystem.theme.Background
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.core.designsystem.theme.PrimaryRed
import com.example.letssopt.core.designsystem.theme.TextPrimary
import com.example.letssopt.core.designsystem.theme.TextSecondary
import com.example.letssopt.core.data.DataStore
import com.example.letssopt.presentation.main.MainActivity
import com.example.letssopt.presentation.signup.SignupActivity

class LoginActivity : ComponentActivity() {

    private val viewModel by viewModels<LoginViewModel>()

    private val signupLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            val email = result.data?.getStringExtra("email") ?: ""
            val password = result.data?.getStringExtra("password") ?: ""
            viewModel.updateRegisteredInfo(email, password)

            Toast.makeText(this, "회원가입 성공!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 자동 로그인 확인
        if (DataStore.getAutoLogin(this)) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            return
        }

        enableEdgeToEdge()
        setContent {
            LETSSOPTTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    LoginScreen(
                        modifier = Modifier.padding(innerPadding),
                        viewModel = viewModel,
                        onSignupClick = {
                            val intent = Intent(this, SignupActivity::class.java)
                            signupLauncher.launch(intent)
                        },
                        onLoginSuccess = {
                            DataStore.setAutoLogin(this, true)
                            Toast.makeText(this, "로그인에 성공했습니다!", Toast.LENGTH_SHORT).show()
                            val intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                            finish()
                        }
                    )
                }
            }
        }
    }
}

@Composable
private fun LoginScreen(
    modifier: Modifier = Modifier,
    viewModel: LoginViewModel = viewModel(),
    onSignupClick: () -> Unit,
    onLoginSuccess: () -> Unit
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
                if (viewModel.checkLoginSuccess()) {
                    onLoginSuccess()
                } else {
                    Toast.makeText(context, "로그인 정보가 올바르지 않습니다.", Toast.LENGTH_SHORT).show()
                }
            },
            enabled = viewModel.isLoginEnabled
        )

    }
}

@Preview(showBackground = true)
@Composable
private fun GreetingPreview2() {
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