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
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.anbui.skribbl.core.presentation.component.SkribblColumn
import com.anbui.skribbl.core.presentation.component.SkribblTextField
import com.anbui.skribbl.core.presentation.theme.Color
import com.anbui.skribbl.feature.game.components.ChatSection
import com.anbui.skribbl.feature.game.components.SkribblCanvas
import com.anbui.skribbl.platform.getScreenWidth
import kotlinx.coroutines.launch
import org.koin.compose.koinInject

class GameScreen : Screen {
    @Composable
    override fun Content() {
        val screenModel: GameScreenModel = koinInject()
        val screenWidth = getScreenWidth()

        val scope = rememberCoroutineScope()

        val drawnPath by screenModel.drawnPath.collectAsState()
        val drawingPath by screenModel.drawingPath.collectAsState()
        val chat by screenModel.chat.collectAsState()

        val drawerState = androidx.compose.material3.rememberDrawerState(DrawerValue.Closed)

        ModalNavigationDrawer(
            drawerContent = {
                ModalDrawerSheet {
                    Text("Drawer title", modifier = Modifier.padding(16.dp))
                    Divider()
                    NavigationDrawerItem(
                        label = { Text(text = "Drawer Item") },
                        selected = false,
                        onClick = { /*TODO*/ }
                    )
                }
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
                        modifier = Modifier.height(screenWidth.dp)
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
                        Divider(thickness = 2.dp)
                        //

                        ChatSection(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(2f),
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
                            onVoice = {}
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
                        onValueChange = screenModel::onChangeChat
                    )
                    IconButton(
                        onClick = screenModel::sendChat
                    ) {
                        Icon(Icons.AutoMirrored.Default.Send, "")
                    }
                }
            }
        }
    }
}

// TODO drag
