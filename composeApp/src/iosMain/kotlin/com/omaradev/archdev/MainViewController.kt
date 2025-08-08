package com.omaradev.archdev

import androidx.compose.ui.window.ComposeUIViewController
import com.omaradev.di.initKoin


fun MainViewController() = ComposeUIViewController(
    configure = {
     //   initKoin()
    }
) {
    App()
}