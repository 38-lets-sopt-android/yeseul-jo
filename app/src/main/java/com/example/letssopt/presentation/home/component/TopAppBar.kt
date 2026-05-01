package com.example.letssopt.presentation.home.component

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.toRoute
import com.example.letssopt.R
import com.example.letssopt.core.designsystem.theme.Background
import com.example.letssopt.core.designsystem.theme.PrimaryRed
import com.example.letssopt.core.navigation.Route

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()

    // 현재 경로
    val currentRoute = try {
        navBackStackEntry?.toRoute<Route>()
    } catch (e: Exception) {
        null
    }

    val isMainScreen = when (currentRoute) {
        is Route.HOME, is Route.STORE, is Route.WEBTOON,
        is Route.SEARCH, is Route.LIBRARY -> true
        else -> false
    }

    if (!isMainScreen) return

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
                IconButton(
                    onClick = {},
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_top_watch),
                        "Watch"
                    )
                }
                IconButton(
                    onClick = {},
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_top_noti),
                        "Noti"
                    )
                }
                IconButton(
                    onClick = {},
                    modifier = Modifier.size(24.dp)
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.ic_top_profile),
                        "Profile"
                    )
                }
            }
        })
}