package com.example.randomnumber.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.randomnumber.R
import com.example.randomnumber.logics.ListDialog
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun RandomListItem(modifier: Modifier = Modifier) {
    val list = remember { mutableStateListOf("") }
    var newItem by remember { mutableStateOf("") }
    var ranItem by remember { mutableStateOf("") }
    var ran by remember { mutableStateOf(false) }
    var expand by remember { mutableStateOf(false) }

    val context = LocalContext.current

    Row(
        modifier
            .fillMaxWidth()
            .size(0.dp, 60.dp)
            .padding(start = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(
            value = newItem,
            onValueChange = { newItem = it },
            maxLines = 1,
            modifier = modifier.weight(2f),
            keyboardOptions = KeyboardOptions.Default.copy(imeAction = ImeAction.Done),
            keyboardActions = KeyboardActions(onDone = {
                list.add(newItem)
                list.remove("")
                newItem = ""
            })
        )
        Spacer(Modifier.width(10.dp))
        LazyColumn(
            modifier
                .weight(0.75f)
                .clickable {
                    if (!list.contains("")) expand = true
                }) {
            items(list.asReversed()) {
                Text(it, color = MaterialTheme.colorScheme.primary, fontSize = 12.sp)
            }
        }
    }
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            if (ran) ranItem else newItem,
            fontSize = 150.sp,
            color = MaterialTheme.colorScheme.primaryContainer,
            modifier = modifier.padding(horizontal = 12.dp),
            fontFamily = FontFamily(Font(R.font.oi_playful)),
            textAlign = TextAlign.Center
        )
    }
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            if (ran) ranItem else newItem,
            fontSize = 70.sp,
            color = MaterialTheme.colorScheme.onPrimaryContainer,
            modifier = modifier.padding(start = 12.dp, end = 12.dp, bottom = 24.dp),
            textAlign = TextAlign.Center,
            style = TextStyle(lineHeight = 55.sp)
        )
    }
    Row(
        modifier
            .fillMaxSize()
            .padding(vertical = 24.dp),
        verticalAlignment = Alignment.Bottom,
        horizontalArrangement = Arrangement.Center
    ) {
        Button(onClick = {
            if (!list.contains("") || list.isEmpty()) {
                ran = true
                ranItem = list.random()
            }
        }) { Icon(Icons.Default.PlayArrow, "", modifier = modifier.size(45.dp, 45.dp)) }
    }
    if (expand) {
        ListDialog(modifier, onDismiss = { expand = false }, onExport = { updatedlist ->
            list.clear()
            list.addAll(updatedlist)
        }, list)
    }
}