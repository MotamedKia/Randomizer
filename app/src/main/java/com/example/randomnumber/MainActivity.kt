package com.example.randomnumber

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.compose.RandomNumberTheme
import com.example.randomnumber.logics.NavigationDialog
import com.example.randomnumber.screens.NavGraphs
import com.example.randomnumber.screens.destinations.DiceRollerDestination
import com.example.randomnumber.screens.destinations.RandomListItemDestination
import com.example.randomnumber.screens.destinations.RandomNumberDestination
import com.orhanobut.hawk.Hawk
import com.ramcosta.composedestinations.DestinationsNavHost
import com.ramcosta.composedestinations.navigation.navigate

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val context = LocalContext.current
            val activity = LocalActivity.current
            var showNavDialog by remember { mutableStateOf(false) }
            val navController = rememberNavController()

            Hawk.init(context).build()

            RandomNumberTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    topBar = {
                        CenterAlignedTopAppBar(title = {
                            Text("")
                        }, navigationIcon = {
                            IconButton(onClick = {
                                showNavDialog = true
//                                Toast.makeText(context, "TBD", Toast.LENGTH_SHORT).show()
                            }) {
                                Icon(
                                    Icons.Default.Menu,
                                    ""
                                )
                            }
                        })
                    }) { innerPadding ->
                    DestinationsNavHost(
                        navGraph = NavGraphs.root, navController = navController,
                        modifier = Modifier.padding(innerPadding)
                    )
                    if (showNavDialog) {
                        NavigationDialog(onDismiss = { showNavDialog = false }, onNav = { opt ->
                            when (opt) {
                                1 -> navController.navigate(RandomNumberDestination)
                                2 -> navController.navigate(RandomListItemDestination)
                                3 -> navController.navigate(DiceRollerDestination())
                                else -> Toast.makeText(context, "Invalid", Toast.LENGTH_SHORT)
                                    .show()
                            }
                            showNavDialog = false
                        })
                    }
                    BackHandler(enabled = true) { activity?.finish() }
                }
            }
        }
    }
}