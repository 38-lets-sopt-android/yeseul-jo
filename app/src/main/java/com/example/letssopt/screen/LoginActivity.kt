package com.example.letssopt.screen

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Surface
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import com.example.letssopt.R
import com.example.letssopt.designsystem.component.button.LabeledTextField
import com.example.letssopt.designsystem.component.button.PrimaryButton
import com.example.letssopt.designsystem.theme.Background
import com.example.letssopt.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.designsystem.theme.PrimaryRed
import com.example.letssopt.designsystem.theme.TextPrimary
import com.example.letssopt.designsystem.theme.TextSecondary

class LoginActivity : ComponentActivity() {

    private var registeredEmail by mutableStateOf("")
    private var registeredPassword by mutableStateOf("")

    private val signupLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            registeredEmail = result.data?.getStringExtra("email") ?: ""
            registeredPassword = result.data?.getStringExtra("password") ?: ""
            Toast.makeText(this, "회원가입 성공!", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            LETSSOPTTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Login(
                        modifier = Modifier.padding(innerPadding),
                        registeredEmail = registeredEmail,
                        registeredPassword = registeredPassword,
                        onSignupClick = {
                            val intent = Intent(this, SignupActivity::class.java)
                            signupLauncher.launch(intent)
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun Login(
    modifier: Modifier = Modifier,
    registeredEmail: String,
    registeredPassword: String,
    onSignupClick: () -> Unit
) {
    val context = LocalContext.current as Activity

    // 사용자가 입력한 값
    var email by remember {
        mutableStateOf("")
    }
    var password by remember {
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
            text = stringResource(R.string.login_title),
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
                placeholder = stringResource(R.string.password_placeholder),
                isPassword = true
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // bottom
        Column(
            verticalArrangement = Arrangement.spacedBy(20.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(R.string.signup_question),
                color = TextSecondary,
                style = typography.labelSmall,
                modifier = Modifier.clickable { onSignupClick() }
            )
            PrimaryButton(
                text = stringResource(R.string.login),
                onClick = {
                    if (email.trim() == registeredEmail.trim() && password.trim() == registeredPassword.trim()) {
                        Toast.makeText(context, "로그인에 성공했습니다!", Toast.LENGTH_SHORT).show()
                        val intent = Intent(context, MainActivity::class.java)
                        context.startActivity(intent)
                        context.finish()
                    } else {
                        Toast.makeText(context, "로그인 정보가 올바르지 않습니다.", Toast.LENGTH_SHORT).show()
                    }
                },
                enabled = email.isNotEmpty() && password.isNotEmpty()
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    LETSSOPTTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Background
        ) {
            Login(
                registeredEmail = "sopt@sopt.org",
                registeredPassword = "password123",
                onSignupClick = {}
            )
        }
    }
}