package com.example.randomnumber.logics

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun NavigationDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    onNav: (opt: Int) -> Unit
) {
    Dialog(onDismissRequest = onDismiss) {
        Card() {
            Column(
                modifier.padding(12.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                TextButton(onClick = { onNav(1) }) { Text("Random Number") }
                TextButton(onClick = { onNav(2) }) { Text("Random Text") }
            }
        }
    }
}