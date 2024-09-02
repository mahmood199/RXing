package com.app.rxjavaplayground

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.app.rxjavaplayground.ui.theme.RxJavaPlaygroundTheme

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Text(
            text = "Hello $name!",
        )

        Button(onClick = { }) {
            Text(text = "Check Click")
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RxJavaPlaygroundTheme {
        Column(modifier = Modifier.fillMaxSize()) {
            Greeting("Android")
        }
    }
}