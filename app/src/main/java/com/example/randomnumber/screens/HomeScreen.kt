package com.example.randomnumber.screens

import android.widget.Toast
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.randomnumber.screens.destinations.DiceRollerDestination
import com.example.randomnumber.screens.destinations.DiceRollerDestination.invoke
import com.example.randomnumber.screens.destinations.RandomListItemDestination
import com.example.randomnumber.screens.destinations.RandomNumberDestination
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.annotation.Destination
import com.ramcosta.composedestinations.navigation.DestinationsNavigator
import com.ramcosta.composedestinations.navigation.navigate

@Destination(start = true)
@Composable
fun HomeScreen(modifier: Modifier = Modifier, destinationsNavigator: DestinationsNavigator) {

    val context = LocalContext.current
    val destines = listOf(1, 2, 3)

    LazyVerticalGrid(columns = GridCells.Fixed(2), contentPadding = PaddingValues(10.dp)) {
        items(destines) {
            val destineName = when (it) {
                1 -> "Random Number"
                2 -> "Random Word"
                3 -> "Roll a Die"
                else -> "Whaa"
            }
            OutlinedCard(modifier.clickable(onClick = {
                when (it) {
                    1 -> destinationsNavigator.navigate(RandomNumberDestination)
                    2 -> destinationsNavigator.navigate(RandomListItemDestination)
                    3 -> destinationsNavigator.navigate(DiceRollerDestination())
                    else -> Toast.makeText(context, "Invalid", Toast.LENGTH_SHORT)
                        .show()
                }
            })) {
                Column(
                    modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier.height(10.dp))
                    Icon(Icons.Default.Home, "", Modifier.size(80.dp))
                    Spacer(modifier.height(8.dp))
                    Text(destineName)
                    Spacer(modifier.height(10.dp))
                }
            }
        }
    }
}