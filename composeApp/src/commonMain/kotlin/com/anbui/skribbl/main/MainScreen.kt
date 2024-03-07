package com.anbui.skribbl.main

import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.anbui.skribbl.core.utils.ProvideAppNavigator
import com.anbui.skribbl.feature.username.UsernameScreen
import kotlinx.coroutines.flow.filterNotNull
import org.koin.compose.koinInject

@Composable
fun MainScreen() {
    val hostState = remember { SnackbarHostState() }
    val screenModel: MainScreenModel = koinInject()
    LaunchedEffect(Unit) {
        screenModel.snackBarRepository.message.filterNotNull().collect {
            hostState.showSnackbar(it)
        }
    }
    Scaffold(
        snackbarHost = {
            SnackbarHost(hostState)
        }
    ) {

    }
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