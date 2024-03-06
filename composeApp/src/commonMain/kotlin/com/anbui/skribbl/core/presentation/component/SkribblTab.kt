package com.anbui.skribbl.core.presentation.component

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import cafe.adriel.voyager.navigator.tab.Tab
import cafe.adriel.voyager.navigator.tab.TabOptions
import com.anbui.skribbl.feature.game.GameScreen
import com.anbui.skribbl.feature.start.StartScreen
import org.jetbrains.compose.resources.painterResource
import skribbl.composeapp.generated.resources.Res
import skribbl.composeapp.generated.resources.calendar_outlined
import skribbl.composeapp.generated.resources.home_outlined

internal sealed interface SkribblTab {
    object StartTab : Tab {
        @Composable
        override fun Content() {
            StartScreen()
        }

        override val options: TabOptions
            @Composable
            get() {
                val title = "Start"
                val icon = painterResource(Res.drawable.home_outlined)
                return remember {
                    TabOptions(
                        index = 0u,
                        title = title,
                        icon = icon
                    )
                }
            }

    }
    object GameTab : Tab {
        @Composable
        override fun Content() {
            GameScreen()
        }


        override val options: TabOptions
            @Composable
            get() {
                val title = "Game"
                val icon = painterResource(Res.drawable.calendar_outlined)
                return remember {
                    TabOptions(
                        index = 1u,
                        title = title,
                        icon = icon
                    )
                }
            }

    }
}