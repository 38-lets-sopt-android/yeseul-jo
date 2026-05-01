package com.example.letssopt.presentation.home.component

import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.toRoute
import com.example.letssopt.core.designsystem.theme.Background
import com.example.letssopt.core.designsystem.theme.Disabled
import com.example.letssopt.core.navigation.Route
import com.example.letssopt.presentation.home.model.BottomNavItem

@Composable
fun BottomNavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    // 현재 경로
    val currentRoute = try {
        navBackStackEntry?.toRoute<Route>()
    } catch (e: Exception) {
        null
    }

    // 로그인, 회원가입 화면에서는 노출하지 않음
    if (currentRoute is Route.LOGIN || currentRoute is Route.SIGNUP || currentRoute == null) {
        return
    }

    NavigationBar(
        containerColor = Background,
        tonalElevation = 0.dp
    ) {
        val currentTab = BottomNavItem.fromRoute(currentRoute)

        BottomNavItem.entries.forEach { item ->
            // 현재 경로와 비교
            val isSelected = currentTab == item
            val tabLabel = stringResource(id = item.title)

            NavigationBarItem(
                selected = isSelected,
                onClick = {
                    // 중복 스택 방지
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
                        imageVector = ImageVector.vectorResource(id = item.icon),
                        contentDescription = null,
                        tint = if (isSelected) Color.White else Disabled
                    )
                },
                label = {
                    Text(
                        text = tabLabel,
                        color = if (isSelected) Color.White else Disabled
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}