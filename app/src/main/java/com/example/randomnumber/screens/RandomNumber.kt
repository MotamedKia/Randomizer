package com.example.randomnumber.screens

import android.widget.Toast
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.randomnumber.R
import com.ramcosta.composedestinations.annotation.Destination

@Destination(start = true)
@Composable
fun RandomNumber(modifier: Modifier = Modifier) {
    /*var i by remember { mutableStateOf(0) }
    var iTriggered by remember { mutableStateOf(false) }
    var iError by remember { mutableStateOf(false) }*/ //TODO make glitch effect

    var lower by remember { mutableStateOf(0) }
    var lowerString by remember { mutableStateOf(lower.toString()) }
    val lowerLabel by remember { mutableStateOf("From") }
    var higher by remember { mutableStateOf(10) }
    var higherString by remember { mutableStateOf(higher.toString()) }
    val higherLabel by remember { mutableStateOf("To") }
    var mid by remember { mutableStateOf((0..10).random()) }

    val context = LocalContext.current
    val activity = LocalActivity.current //TODO the glitch's final boss: activity?.finish

    Column(
        modifier
            .fillMaxSize()
        /*.clickable {
            i++
            when (i) {
                3 -> {
                    iTriggered = true
                }

                10 -> {
                    iError = true
                    iTriggered = false
                }

                30 -> {
                    i = 0
                    iTriggered = false
                    iError = false
                }
            }
        }*/,
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            mid.toString(),
            fontFamily = FontFamily(Font(R.font.oi_playful)),
            fontSize = 300.sp,
            color = MaterialTheme.colorScheme.primaryContainer,
            textAlign = TextAlign.Center
        )
    }
    Row(
        modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.Center
    ) {
        OutlinedTextField(value = lowerString, onValueChange = {
            lowerString = it
        }, label = { Text(lowerLabel) }, modifier = modifier.weight(1f))
        OutlinedTextField(value = higherString, onValueChange = {
            higherString = it
        }, label = { Text(higherLabel) }, modifier = modifier.weight(1f))
    }
    Column(
        modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            mid.toString(),
            fontSize = 100.sp,
            color = MaterialTheme.colorScheme.onPrimaryContainer
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
            try {
                lower = lowerString.toInt()
                higher = higherString.toInt()
                if (higher > lower) {
                    mid = (lower..higher).random()
                } else {
                    Toast.makeText(context, "Double check the numbers", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Make sure you entered numbers", Toast.LENGTH_SHORT).show()
            }
        }) { Icon(Icons.Default.PlayArrow, "", modifier = modifier.size(45.dp, 45.dp)) }
    }
}