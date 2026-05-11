package com.example.app_native_store

import AppNavigation
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.example.app_native_store.ui.theme.App_native_storeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            App_native_storeTheme {
                AppNavigation()
            }
        }
    }
}

