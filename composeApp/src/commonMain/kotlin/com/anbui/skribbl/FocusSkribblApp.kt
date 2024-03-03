package com.anbui.skribbl

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.SlideTransition
import com.anbui.skribbl.core.presentation.theme.SkribblTheme
import com.anbui.skribbl.core.utils.ProvideAppNavigator
import com.anbui.skribbl.main.MainScreen
import org.jetbrains.compose.resources.ExperimentalResourceApi
import org.koin.compose.KoinContext

@OptIn(ExperimentalResourceApi::class)
@Composable
fun FocusSkribblApp() {
    KoinContext {
        SkribblTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                Navigator(
                    screen = MainScreen(),
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
    }

}