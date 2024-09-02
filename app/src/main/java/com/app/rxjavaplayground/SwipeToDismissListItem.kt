package com.app.rxjavaplayground

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.app.rxjavaplayground.ui.theme.RxJavaPlaygroundTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SwipeToDismissListItem(
    modifier: Modifier = Modifier,
    onEndToStart: () -> Unit = {},
    onStartToEnd: () -> Unit = {},
    content: @Composable () -> Unit,
) {

    val dismissedState = rememberSwipeToDismissBoxState()

    SwipeToDismissBox(
        state = dismissedState,
        modifier = modifier
            .fillMaxWidth()
            .height(IntrinsicSize.Min),
        backgroundContent = {

            val color by animateColorAsState(
                targetValue = when (dismissedState.targetValue) {
                    SwipeToDismissBoxValue.StartToEnd -> Color.LightGray
                    SwipeToDismissBoxValue.EndToStart -> Color.Green
                    SwipeToDismissBoxValue.Settled -> Color.Red
                },
                label = "swipe"
            )

            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .drawBehind {
                        drawRect(color = color)
                    }
            ) {
                when (dismissedState.targetValue) {
                    SwipeToDismissBoxValue.StartToEnd -> {
                        Icon(
                            imageVector = androidx.compose.material.icons.Icons.Default.Edit,
                            contentDescription = "edit",
                            modifier = Modifier
                                .align(androidx.compose.ui.Alignment.CenterStart)
                                .fillMaxWidth()
                                .aspectRatio(1f)
                                .padding(start = 16.dp)
                        )
                    }

                    SwipeToDismissBoxValue.EndToStart -> {
                        Icon(
                            imageVector = androidx.compose.material.icons.Icons.Default.Delete,
                            contentDescription = "delete",
                            modifier = Modifier
                                .align(androidx.compose.ui.Alignment.CenterEnd)
                                .fillMaxWidth()
                                .aspectRatio(1f)
                                .padding(end = 16.dp)
                        )
                    }

                    SwipeToDismissBoxValue.Settled -> {
                    }
                }
            }
        },
    ) {
        content()
    }

    when (dismissedState.currentValue) {
        SwipeToDismissBoxValue.EndToStart -> {
            LaunchedEffect(dismissedState.currentValue) {
                onEndToStart()

                // 6. Don't forget to reset the state value
                dismissedState.snapTo(SwipeToDismissBoxValue.Settled) // or dismissState.reset()
            }

        }

        SwipeToDismissBoxValue.StartToEnd -> {
            LaunchedEffect(dismissedState.currentValue) {
                onStartToEnd()
                dismissedState.snapTo(SwipeToDismissBoxValue.Settled) // or dismissState.reset()
            }
        }

        SwipeToDismissBoxValue.Settled -> {
            // Nothing to do
        }
    }
}

@Preview
@Composable
private fun SwipeToDismissListItemPreview() {
    RxJavaPlaygroundTheme {
        SwipeToDismissListItem(modifier = Modifier) {

        }
    }
}