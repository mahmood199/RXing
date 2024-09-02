package com.app.rxjavaplayground

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun SwipeableListItemsUI(
    myList: List<EmojiItem>,
    onItemRemoved: (Int) -> Unit,
    modifier: Modifier = Modifier,
) {
    LazyColumn(
        modifier = modifier
    ) {
        itemsIndexed(
            key = { index, _ -> index },
            items = myList
        ) { index, item ->
            SwipeToDismissListItem(
                modifier = Modifier.animateItemPlacement(),
                onEndToStart = {
                    onItemRemoved(index)
                },
            ) {
                ListItem(
                    headlineContent = {
                        Text(
                            text = item.description,
                            style = MaterialTheme.typography.titleMedium
                        )
                    },
                    leadingContent = {
                        Text(
                            text = item.emoji,
                            style = MaterialTheme.typography.displayMedium
                        )
                    }
                )


            }

            if (myList.size != index) {
                HorizontalDivider()
            }
        }
    }

}