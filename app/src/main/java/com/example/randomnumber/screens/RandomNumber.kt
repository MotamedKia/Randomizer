package com.example.randomnumber.screens

import android.widget.Toast
import androidx.activity.compose.LocalActivity
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.randomnumber.R
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun RandomNumber(modifier: Modifier = Modifier) {
    /*var i by remember { mutableStateOf(0) }
    var iTriggered by remember { mutableStateOf(false) }
    var iError by remember { mutableStateOf(false) }*/ //TODO make glitch effect

    var floated by remember { mutableStateOf(false) }
    var lower by remember { mutableStateOf(if (floated) 0.00 else 0) }
    var lowerString by remember { mutableStateOf(lower.toString()) }
    val lowerLabel by remember { mutableStateOf("From") }
    var higher by remember { mutableStateOf(if (floated) 10.00 else 10) }
    var higherString by remember { mutableStateOf(higher.toString()) }
    val higherLabel by remember { mutableStateOf("To") }
    var mid by remember { mutableStateOf((0..10).random().toString()) }

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
            mid,
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
                if (floated) {
                    lower = lowerString.toDouble()
                    val lowerParts = lower.toString().split(".")
                    val lowerPart = lowerParts[0].toInt()
                    higher = higherString.toDouble()
                    val higherParts = higher.toString().split(".")
                    val higherPart = higherParts[0].toInt()
                    if (higher as Double > lower as Double) {
                        val randomChange = (1..99).random()
                        val midString =
                            (lowerPart..higherPart).random().toString() + "." + randomChange
                        mid = midString.toDouble().toString()
                    } else {
                        Toast.makeText(context, "Double check the numbers", Toast.LENGTH_SHORT)
                            .show()
                    }
                } else {
                    lower = lowerString.toInt()
                    higher = higherString.toInt()
                    if (higher as Int > lower as Int) {
                        mid = (lower as Int..higher as Int).random().toString()
                    } else {
                        Toast.makeText(context, "Double check the numbers", Toast.LENGTH_SHORT)
                            .show()
                    }
                }
            } catch (e: Exception) {
                Toast.makeText(context, "Make sure you entered numbers", Toast.LENGTH_SHORT).show()
            }
        }) { Icon(Icons.Default.PlayArrow, "", modifier = modifier.size(45.dp, 45.dp)) }
    }
    Column(
        Modifier
            .fillMaxSize()
            .padding(horizontal = 20.dp, vertical = 5.dp),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.Start
    ) {
        Switch(!floated, {
            floated = !floated
            lower = if (floated) 0.00 else 0
            lowerString = lower.toString()
            higher = if (floated) 10.00 else 10
            higherString = higher.toString()
        })
        Text(
            if (floated) "double" else "integer",
            color = if (floated) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.primary,
            fontSize = 12.sp
        )
    }
}