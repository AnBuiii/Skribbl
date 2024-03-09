package com.anbui.skribbl

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.anbui.skribbl.core.presentation.theme.SkribblTheme
import com.anbui.skribbl.main.MainScreen
import org.koin.compose.KoinContext

@Composable
fun FocusSkribblApp() {
    KoinContext {
        SkribblTheme {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colorScheme.background
            ) {
                MainScreen()
            }
        }
    }
}
