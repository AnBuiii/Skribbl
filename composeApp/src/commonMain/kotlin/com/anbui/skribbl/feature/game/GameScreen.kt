package com.anbui.skribbl.feature.game

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import com.anbui.skribbl.core.presentation.component.SkribblColumn
import com.anbui.skribbl.core.presentation.component.SkribblTextField
import com.anbui.skribbl.core.presentation.theme.Color
import com.anbui.skribbl.feature.game.components.AnnouncementItem
import com.anbui.skribbl.feature.game.components.SkribblCanvas
import org.koin.compose.koinInject

class GameScreen : Screen {
    @OptIn(ExperimentalComposeUiApi::class)
    @Composable
    override fun Content() {
        val screenModel: GameScreenModel = koinInject()
//
//        val a = LocalWindowInfo.current
//        val height = a.containerSize.height

        val drawnPath by screenModel.drawnPath.collectAsState()
        val drawingPath by screenModel.drawingPath.collectAsState()
        val chat by screenModel.chat.collectAsState()


        Box(

        ) {
            SkribblColumn(
                modifier = Modifier.fillMaxSize().padding(bottom = 56.dp)
            ) {
                SkribblCanvas(
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(3f)
                        .background(Color.Yellow),
                    drawnPath = drawnPath,
                    drawingPath = drawingPath,
                    onBeginDraw = screenModel::onBeginDraw,
                    onEndDraw = screenModel::onEndDraw,
                    onDraw = screenModel::onDraw

                )
                //

                Divider(thickness = 2.dp)
                Row(
                    modifier = Modifier.fillMaxWidth().weight(2f)
                ) {
                    Column {
                        IconButton(
                            onClick = screenModel::sendChat
                        ) {
                            Icon(Icons.Default.Person, "")
                        }
                        IconButton(
                            onClick = screenModel::sendChat
                        ) {
                            Icon(Icons.Default.Call, "")
                        }
                    }
                    LazyColumn(
                        modifier = Modifier.background(Color.Yellow)
                    )
                    {
                        items(100) {
                            AnnouncementItem("asdasdasdasd")
                        }
                    }
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

// TODO drag

