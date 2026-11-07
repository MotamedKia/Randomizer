package com.example.randomnumber.screens

import android.widget.Toast
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.randomnumber.R
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

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        contentPadding = PaddingValues(10.dp),
        modifier = modifier.fillMaxSize()
    ) {
        items(destines) {
            val destineName = when (it) {
                1 -> "Random Number"
                2 -> "Random Word"
                3 -> "Roll a Die"
                else -> "Whaa"
            }
            val ranImageList = (0..3).random()
            val ranImageNumber = (0..3).random()
            val ranImageDice = (0..3).random()
            val imageList = when (ranImageList) {
                0 -> painterResource(id = R.drawable.word_four)
                1 -> painterResource(id = R.drawable.word_three)
                2 -> painterResource(id = R.drawable.word_two)
                3 -> painterResource(id = R.drawable.word_one)
                else -> painterResource(id = R.drawable.randomizer_logo_png)
            }
            val imageNumber = when (ranImageNumber) {
                0 -> painterResource(id = R.drawable.number_four)
                1 -> painterResource(id = R.drawable.number_three)
                2 -> painterResource(id = R.drawable.number_two)
                3 -> painterResource(id = R.drawable.number_one)
                else -> painterResource(id = R.drawable.randomizer_logo_png)
            }
            val imageDice = when (ranImageDice) {
                0 -> painterResource(id = R.drawable.dice_four)
                1 -> painterResource(id = R.drawable.dice_three)
                2 -> painterResource(id = R.drawable.dice_two)
                3 -> painterResource(id = R.drawable.dice_one)
                else -> painterResource(id = R.drawable.randomizer_logo_png)
            }
            OutlinedCard(
                modifier
                    .padding(5.dp)
                    .clickable(onClick = {
                        when (it) {
                            1 -> destinationsNavigator.navigate(RandomNumberDestination)
                            2 -> destinationsNavigator.navigate(RandomListItemDestination)
                            3 -> destinationsNavigator.navigate(DiceRollerDestination())
                            else -> Toast.makeText(context, "Invalid", Toast.LENGTH_SHORT)
                                .show()
                        }
                    }),
                border = BorderStroke(width = 2.dp, color = MaterialTheme.colorScheme.secondary)
            ) {
                Column(
                    modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier.height(10.dp))
                    Image(
                        painter = when (it) {
                            1 -> imageNumber
                            2 -> imageList
                            3 -> imageDice
                            else -> painterResource(id = R.drawable.randomizer_logo_png)
                        },
                        contentDescription = "My Image",
                        modifier = Modifier
                            .size(130.dp),
                        contentScale = ContentScale.Crop
                    )
                    Spacer(modifier.height(8.dp))
                    Text(destineName)
                    Spacer(modifier.height(10.dp))
                }
            }
        }
    }
}