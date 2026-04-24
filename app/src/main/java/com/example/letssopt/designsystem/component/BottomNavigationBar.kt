package com.example.letssopt.designsystem.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.letssopt.designsystem.theme.Background
import com.example.letssopt.designsystem.theme.Disabled
import com.example.letssopt.navigation.BottomNavItem
import com.example.letssopt.navigation.NavigationType

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navigationTabList = listOf(
        BottomNavItem.Home,
        BottomNavItem.Store,
        BottomNavItem.Webtoon,
        BottomNavItem.Search,
        BottomNavItem.Library
    )

    NavigationBar(
        containerColor = Background,
        tonalElevation = 0.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route ?: NavigationType.HOME

        navigationTabList.forEach { item ->
            val isSelected = currentRoute == item.route
            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.findStartDestination().id) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.icon),
                        contentDescription = stringResource(id = item.title),
                        tint = if (isSelected) Color.White else Disabled
                    )
                },
                label = {
                    Text(
                        text = stringResource(id = item.title),
                        color = if (isSelected)  Color.White else Disabled
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}