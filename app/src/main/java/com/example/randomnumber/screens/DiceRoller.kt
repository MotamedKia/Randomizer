package com.example.randomnumber.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.randomnumber.logics.Dice
import com.ramcosta.composedestinations.annotation.Destination

@Destination
@Composable
fun DiceRoller(modifier: Modifier = Modifier) {
    var isRolling by remember { mutableStateOf(false) }
    var cheched by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        Dice(
            sides = if (!cheched) 6 else 20,
            isRolling = isRolling
        )

        Spacer(modifier = Modifier.height(24.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Switch(
                checked = cheched,
                onCheckedChange = { cheched = !cheched }
            )
            Spacer(modifier = Modifier.width(15.dp))
            Button(
                onClick = {
                    isRolling = true
                },
                enabled = !isRolling
            ) {
                Text("Roll Dice")
            }
        }
    }
}