package com.example.randomnumber.logics

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog

@Composable
fun UpdateListStringDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    yes: () -> Unit,
    no: () -> Unit,
) {
    Dialog(onDismissRequest = { onDismiss() }) {
        Column(
            modifier.fillMaxSize().clickable{onDismiss()},
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedCard(modifier.padding(15.dp)) {
                Column(verticalArrangement = Arrangement.Top, horizontalAlignment = Alignment.CenterHorizontally, modifier = modifier.padding(5.dp)) {
                    Text("This new list is similar to an existing favorite list.")
                    Text("Do you want to merge and update the old one?")
                    Row() {
                        Button(onClick = { yes() }) { Text("Update") }
                        Spacer(modifier.padding(10.dp))
                        Button(onClick = { no() }) { Text("Add a new list") }
                    }
                }
            }
        }
    }
}

fun checkIfNew(newList: List<String>, dbList: List<List<String>>): Int {

    dbList.forEach { favoriteList ->

        // Check for identical contents, regardless of order.
        if (newList.size == favoriteList.size &&
            newList.toSet() == favoriteList.toSet()
        ) {
            return 3
        }
    }

    dbList.forEach { favoriteList ->

        // Check whether the lists share at least one element.
        if (newList.toSet().intersect(favoriteList.toSet()).isNotEmpty()) {
            return 2
        }
    }

    // No matching or similar list found.
    return 1
}