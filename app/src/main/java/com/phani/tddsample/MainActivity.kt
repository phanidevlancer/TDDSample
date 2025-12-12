package com.phani.tddsample

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.rememberNavController
import com.phani.tddsample.navigation.NavGraph
import com.phani.tddsample.ui.theme.TDDSampleTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TDDSampleTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}