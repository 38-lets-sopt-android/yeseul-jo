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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.letssopt.R
import com.example.letssopt.core.designsystem.component.PrimaryButton
import com.example.letssopt.core.designsystem.theme.Background
import com.example.letssopt.core.designsystem.theme.LETSSOPTTheme
import com.example.letssopt.core.designsystem.theme.TextPrimary
import com.example.letssopt.core.designsystem.theme.TextSecondary

@Composable
fun ProfileScreen(
    modifier: Modifier = Modifier
) {
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
                value = "아이디아이디"
            )
            ProfileInfoItem(
                label = R.string.profile_name,
                value = "이름이름",
            )
            ProfileInfoItem(
                label = R.string.profile_email,
                value = "이메일이메일"
            )
            ProfileInfoItem(
                label = R.string.profile_age,
                value = "1234"
            )
            ProfileInfoItem(
                label = R.string.profile_part,
                value = "파트파트"
            )
        }

        PrimaryButton(
            text = stringResource(R.string.profile_other_users),
            onClick = {}
        )
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
            ProfileScreen()
        }
    }
}