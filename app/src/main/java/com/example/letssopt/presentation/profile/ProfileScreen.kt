package com.example.letssopt.presentation.profile

import androidx.annotation.StringRes
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.letssopt.R
import com.example.letssopt.core.designsystem.component.PrimaryButton
import com.example.letssopt.core.designsystem.theme.Background
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.core.designsystem.theme.TextPrimary
import com.example.letssopt.core.designsystem.theme.TextSecondary

@Composable
fun ProfileScreen(
    userId: Int,
    modifier: Modifier = Modifier,
    viewModel: ProfileViewModel = viewModel()
) {
    val state by viewModel.uiState.collectAsStateWithLifecycle()

    LaunchedEffect(userId) {
        viewModel.fetchUserProfile(userId)
    }

    when (val uiState = state) {
        is ProfileUiState.Success -> {
            val user = uiState.profileData

            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(
                        horizontal = 20.dp
                    ),
                horizontalAlignment = Alignment.Start,
                verticalArrangement = Arrangement.Top
            ) {
                Text(
                    text = stringResource(R.string.profile_title),
                    color = TextPrimary,
                    style = typography.headlineMedium,
                    modifier = Modifier.padding(
                        top = 70.dp,
                        start = 7.dp,
                        bottom = 68.dp
                    )
                )

                Column(
                    modifier = Modifier.padding(bottom = 30.dp),
                    verticalArrangement = Arrangement.spacedBy(30.dp)
                ) {
                    ProfileInfoItem(
                        label = R.string.profile_id,
                        value = user.loginId
                    )
                    ProfileInfoItem(
                        label = R.string.profile_name,
                        value = user.name
                    )
                    ProfileInfoItem(
                        label = R.string.profile_email,
                        value = user.email
                    )
                    ProfileInfoItem(
                        label = R.string.profile_age,
                        value = user.age.toString()
                    )
                    ProfileInfoItem(
                        label = R.string.profile_part,
                        value = user.part
                    )
                }

                PrimaryButton(
                    text = stringResource(R.string.profile_other_users),
                    onClick = {}
                )
            }
        }

        else -> {
        }
    }
}

@Composable
private fun ProfileInfoItem(
    @StringRes label: Int,
    value: String,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier.padding(start = 3.dp)
    ) {
        Text(
            text = stringResource(id = label),
            color = TextPrimary,
            style = typography.labelLarge
        )
        Spacer(modifier = Modifier.height(14.dp))
        Text(
            text = value,
            color = TextSecondary,
            style = typography.labelSmall
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun ProfilePreview() {
    LETSSOPTTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = Background
        ) {
            ProfileScreen(userId = 1)
        }
    }
}