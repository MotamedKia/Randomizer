package com.example.randomnumber.logics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog

@Composable
fun ListDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onExport: (List<String>) -> Unit,
    list: List<String>
) {
    // Make list and expanded mutable and stateful
    val mutableList = remember { mutableStateListOf(*list.toTypedArray()) }
    val expanded = remember { mutableStateListOf(*Array(list.size) { false }) }

    Dialog(onDismissRequest = {
        onExport(mutableList.toList())
        onDismiss()
    }) {
        Card {
            LazyColumn(modifier) {
                items(mutableList) { item ->
                    val index =
                        mutableList.indexOf(item) // Still not ideal for duplicates, but preserved for minimal change
                    Row(
                        modifier
                            .width(250.dp)
                            .padding(vertical = 10.dp),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Center
                    ) {
                        if (expanded.getOrNull(index) == true) {
                            TextButton(onClick = { expanded[index] = false }) {
                                Text(item, fontSize = 20.sp)
                            }
                            Row {
                                IconButton(onClick = {
                                    // Delete item
                                    mutableList.removeAt(index)
                                    expanded.removeAt(index)
                                }) {
                                    Icon(Icons.Default.Delete, "")
                                }
                            }
                        } else {
                            TextButton(onClick = { expanded[index] = true }) {
                                Text(item, fontSize = 20.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}
