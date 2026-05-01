package com.example.letssopt.presentation.home.component

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
import com.example.letssopt.core.designsystem.theme.Background
import com.example.letssopt.core.designsystem.theme.Disabled
import com.example.letssopt.presentation.home.component.BottomNavItem

val navigationTabList = listOf(
    BottomNavItem.Home,
    BottomNavItem.Store,
    BottomNavItem.Webtoon,
    BottomNavItem.Search,
    BottomNavItem.Library
)

@Composable
fun BottomNavigationBar(navController: NavController) {

    NavigationBar(
        containerColor = Background,
        tonalElevation = 0.dp
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route

        navigationTabList.forEach { item ->
            val isSelected = currentRoute == item.route
            val label = stringResource(id = item.title)

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
                        contentDescription = label,
                        tint = if (isSelected) Color.White else Disabled
                    )
                },
                label = {
                    Text(
                        text = label,
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