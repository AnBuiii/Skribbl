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
import com.anbui.skribbl.feature.game.GameScreen
import com.anbui.skribbl.feature.username.UsernameScreen
import org.koin.compose.KoinContext

@Composable
fun FocusSkribblApp() {
    KoinContext {
        SkribblTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
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
    }

}