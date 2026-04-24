package com.example.letssopt.designsystem.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.example.letssopt.R
import com.example.letssopt.designsystem.theme.Background
import com.example.letssopt.designsystem.theme.PrimaryRed

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.logo),
                style = typography.displayLarge,
                modifier = Modifier.padding(start = 15.dp)
            )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
            containerColor = Background,
            titleContentColor = PrimaryRed,
            actionIconContentColor = Color.White
        ),
        actions = {
            Row(
                modifier = Modifier.padding(end = 20.dp),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                IconButton(onClick = {}, modifier = Modifier.size(24.dp)) {
                    Icon(painterResource(id = R.drawable.ic_top_watch), "Watch")
                }
                IconButton(onClick = {}, modifier = Modifier.size(24.dp)) {
                    Icon(painterResource(id = R.drawable.ic_top_noti), "Noti")
                }
                IconButton(onClick = {}, modifier = Modifier.size(24.dp)) {
                    Icon(painterResource(id = R.drawable.ic_top_profile), "Profile")
                }
            }
        })
}