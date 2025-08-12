package com.omaradev.archdev

import androidx.compose.ui.window.ComposeUIViewController
import com.omaradev.archdev.ui.App


fun MainViewController() = ComposeUIViewController(
    configure = {
     //   initKoin()
    }
) {
    App()
}