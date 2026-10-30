package com.example.randomnumber.logics

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.VerticalDivider
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import com.orhanobut.hawk.Hawk

@Composable
fun FavListsDialog(
    modifier: Modifier = Modifier,
    onDismiss: () -> Unit,
    context: Context,
    onDelete: (List<String>) -> Unit,
    onConfirm: (List<String>) -> Unit,
) {
    var favedLists by remember { mutableStateOf<List<List<String>>>(emptyList()) }

    LaunchedEffect(key1 = Unit) {
        favedLists = Hawk.get("FavoriteListOfStrings", emptyList())
    }

    Dialog(onDismissRequest = onDismiss) {
        Card(modifier) {
            LazyColumn(
                modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(favedLists) { itList ->
                    Row(
                        modifier = modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        IconButton(onClick = {
                            onDelete(itList)
                            favedLists = Hawk.get("FavoriteListOfStrings", emptyList())
                        }, modifier.weight(1f)) { Icon(Icons.Default.Delete, "") }
                        Spacer(modifier.padding(2.dp))
                        LazyRow(
                            modifier.weight(3f),
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.Center
                        ) {
                            itemsIndexed(itList) { index, itItem ->
                                Text(itItem, color = MaterialTheme.colorScheme.primary)
                                if (index < itList.lastIndex) {
                                    Spacer(modifier.padding(2.dp))
                                    VerticalDivider(
                                        modifier = modifier
                                            .height(20.dp)
                                            .padding(horizontal = 8.dp),
                                        thickness = 1.dp,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                    Spacer(modifier.padding(2.dp))
                                }
                            }
                        }
                        Spacer(modifier.padding(5.dp))
                        IconButton(onClick = {
                            onConfirm(itList)
                        }, modifier.weight(1f)) { Icon(Icons.Default.Check, "") }
                    }
                }
            }
        }
    }
}
