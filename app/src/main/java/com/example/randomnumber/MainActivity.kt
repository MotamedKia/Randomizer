package com.example.randomnumber

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.BackHandler
import androidx.activity.compose.LocalActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.compose.RandomNumberTheme
import com.example.randomnumber.logics.NavigationDialog
import com.example.randomnumber.screens.NavGraphs
import com.example.randomnumber.screens.RandomListItem
import com.example.randomnumber.screens.destinations.RandomListItemDestination
import com.example.randomnumber.screens.destinations.RandomNumberDestination
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
            RandomNumberTheme {
                Scaffold(modifier = Modifier.fillMaxSize(),
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