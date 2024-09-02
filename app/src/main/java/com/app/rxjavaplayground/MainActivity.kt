package com.app.rxjavaplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import com.app.rxjavaplayground.ui.theme.RxJavaPlaygroundTheme

class MainActivity : ComponentActivity() {

    private val TAG = "MainActivity"

    private val viewModel by viewModels<MainViewModel>()

    private val myList = mutableStateListOf<EmojiItem>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RxJavaPlaygroundTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
        showDataFlowOfRxJava()
    }

    private fun showDataFlowOfRxJava() {
        viewModel.creationOperators(Operator.Delay)
    }
}
