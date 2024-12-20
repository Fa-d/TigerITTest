package com.example.tigerittest

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.tigerittest.ui.nav.DummyPostNavGraph
import com.example.tigerittest.ui.theme.TigerITTestTheme
import com.example.tigerittest.ui.viewmodels.PostsViewmodel
import com.example.tigerittest.domain.utils.LocalNavController
import com.example.tigerittest.domain.utils.LocalPostsViewmodel
import dagger.hilt.android.AndroidEntryPoint
import kotlin.getValue

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    val viewModel: PostsViewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController: NavHostController = rememberNavController()
            CompositionLocalProvider(
                LocalNavController provides navController,
                LocalPostsViewmodel provides viewModel,
            ) {
                TigerITTestTheme {
                    Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                        Box(modifier = Modifier.padding(innerPadding)) {
                            DummyPostNavGraph(navController)
                        }
                    }
                }
            }
        }
    }
}

