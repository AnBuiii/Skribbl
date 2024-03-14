package com.anbui.skribbl.feature.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.koin.getScreenModel
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.currentOrThrow
import com.anbui.skribbl.core.presentation.component.SkribblColumn
import com.anbui.skribbl.core.presentation.component.SkribblTextField
import com.anbui.skribbl.core.presentation.theme.Color
import com.anbui.skribbl.feature.game.components.ChatSection
import com.anbui.skribbl.feature.game.components.ChooseWordOverlay
import com.anbui.skribbl.feature.game.components.PlayerDrawerSheet
import com.anbui.skribbl.feature.game.components.SkribblCanvas
import com.anbui.skribbl.feature.game.components.ToolBar
import com.anbui.skribbl.platform.Platform
import kotlinx.coroutines.launch

class GameScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel: GameScreenModel = getScreenModel()
        val screenHeight = Platform.INSTANCE.getScreenHeight()
        val navigator = LocalNavigator.currentOrThrow

        val scope = rememberCoroutineScope()

        val drawnPath by screenModel.drawnPath.collectAsState()
        val drawingPath by screenModel.drawingPath.collectAsState()
        val chat by screenModel.chat.collectAsState()
        val chatMessages by screenModel.chatMessage.collectAsState()
        val showChosenWord by screenModel.showChooseWordOverlay.collectAsState()
        val newWords by screenModel.newWords.collectAsState()
        val players by screenModel.players.collectAsState()
        val playerName by screenModel.playerName.collectAsState()
        val time by screenModel.time.collectAsState()

        val drawerState = rememberDrawerState(DrawerValue.Closed)

        LaunchedEffect(Unit) {
            screenModel.gameEvent.collect { event ->
                if (event == GameScreenEvent.Back) {
                    navigator.pop()
                }
            }
        }

        ModalNavigationDrawer(
            drawerContent = {
                PlayerDrawerSheet(players)
            },
            gesturesEnabled = true,
            drawerState = drawerState
        ) {
            Box {
                SkribblColumn(
                    modifier = Modifier
                        .padding(bottom = 56.dp)
                        .verticalScroll(rememberScrollState(), false)
                ) {
                    Column(
                        modifier = Modifier.height(screenHeight.dp)
                    ) {
                        SkribblCanvas(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(3f)
                                .background(Color.Yellow),
                            drawnPath = drawnPath,
                            drawingPath = drawingPath,
                            onBeginDraw = {
                                screenModel.onEvent(DrawEvent.BeginDraw(it))
                            },
                            onEndDraw = {
                                screenModel.onEvent(DrawEvent.EndDraw)
                            },
                            onDraw = {
                                screenModel.onEvent(DrawEvent.OnDraw(it))
                            }

                        )
                        HorizontalDivider(thickness = 2.dp)

                        ChatSection(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(2f),
                            chatMessages = chatMessages,
                            onOpenPlayerDrawer = {
                                scope.launch {
                                    if (!drawerState.isAnimationRunning) {
                                        if (drawerState.isClosed) {
                                            drawerState.open()
                                        } else {
                                            drawerState.close()
                                        }
                                    }
                                }
                            },
                            onVoice = {},
                            playerName = playerName,
                            time = time
                        )
                    }
                }

                // TextField
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier.align(Alignment.BottomCenter)
                        .background(MaterialTheme.colors.background)
                        .height(56.dp)
                ) {
                    SkribblTextField(
                        modifier = Modifier.weight(1f),
                        text = chat,
                        onValueChange = {
                            screenModel.onEvent(DrawEvent.Chat(it))
                        }
                    )
                    IconButton(
                        onClick = { screenModel.onEvent(DrawEvent.SendChat) }
                    ) {
                        Icon(Icons.AutoMirrored.Default.Send, "")
                    }
                }

                // ToolBar
                ToolBar(
                    modifier = Modifier.align(Alignment.TopCenter),
                    onUndo = {
                        screenModel.onEvent(DrawEvent.Undo)
                    }
                )

                // Overlay
                ChooseWordOverlay(
                    isVisible = showChosenWord,
                    words = newWords,
                    onWordClick = {
                        screenModel.onEvent(DrawEvent.ChooseWord(it))
                    }
                )
            }
        }
    }
}
