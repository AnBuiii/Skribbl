package com.anbui.skribbl.main

import androidx.compose.foundation.background
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.anbui.skribbl.core.presentation.theme.Color
import com.anbui.skribbl.core.utils.ProvideAppNavigator
import com.anbui.skribbl.feature.username.UsernameScreen
import org.koin.compose.koinInject

@Composable
fun MainScreen() {
    val screenModel: MainScreenModel = koinInject()

    Scaffold(
        modifier = Modifier.background(Color.Black),
        snackbarHost = {
            SnackbarHost(screenModel.hostState)
        }
    ) {
        Navigator(
            screen = UsernameScreen(),
            content = { navigator ->
                ProvideAppNavigator(
                    navigator = navigator,
                    content = {
                        SlideTransition(navigator)
                    }
                )
            }
        )
    }
}
