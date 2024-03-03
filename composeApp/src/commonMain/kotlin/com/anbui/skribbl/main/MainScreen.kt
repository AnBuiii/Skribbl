package com.anbui.skribbl.main

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.CurrentScreen
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabNavigator
import com.anbui.skribbl.core.presentation.component.SkribblTab

class MainScreen : Screen {

    @Composable
    override fun Content() {
        TabNavigator(
            SkribblTab.StartTab,
        ) {
            val tabNavigator = LocalTabNavigator.current
            Scaffold(
                bottomBar = {
                    BottomAppBar {
                        TabNavigationItem(SkribblTab.StartTab)
                        TabNavigationItem(SkribblTab.GameTab)
                    }
                },
            ) { innerPadding ->
                Box(
                    modifier = Modifier.padding(innerPadding)
                ) {
                    CurrentScreen()
                }
            }
        }
    }
}

@Composable
private fun RowScope.TabNavigationItem(tab: Tab) {
    val tabNavigator = LocalTabNavigator.current
    val isSelected = tabNavigator.current == tab

    NavigationBarItem(
        modifier = Modifier.offset(
            x = when (tab.options.index) {
                (0u).toUShort() -> 0.dp
                (1u).toUShort() -> (-24).dp
                (2u).toUShort() -> 24.dp
                (3u).toUShort() -> 0.dp
                else -> 0.dp
            },
        ),
        selected = tabNavigator.current == tab,
        onClick = { tabNavigator.current = tab },
        icon = {
            tab.options.icon?.let {
                Icon(
                    painter = if (isSelected) {
                        it
//                        FilledIcon(tab)
                    } else {
                        it
                    },
                    contentDescription = tab.options.title,
                    tint = if (isSelected) {
                        MaterialTheme.colorScheme.primary
                    } else {
                        MaterialTheme.colorScheme.onBackground
                    },
                )
            }
        },
    )
}
