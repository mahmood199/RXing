package com.app.rxjavaplayground

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.app.rxjavaplayground.ui.theme.RxJavaPlaygroundTheme

class MainActivity : ComponentActivity() {

    private val TAG = "MainActivity"

    private val viewModel by viewModels<MainViewModel>()

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

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Hello $name!",
        )

        Button(onClick = {  }) {
            Text(text = "Check Click")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RxJavaPlaygroundTheme {
        Greeting("Android")
    }
}