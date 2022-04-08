package com.example.findtime.android.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Language
import androidx.compose.material.icons.filled.Place
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import com.example.findtime.android.emptyComposable
import com.example.findtime.android.theme.AppTheme
import com.example.findtime.android.topBarFun

sealed class Screen(val title: String) {
    object TimeZonesScreen : Screen("Timezones")
    object FindTimeScreen : Screen("Find Time")
}

data class BottomNavigationItem(
    val route: String,
    val icon: ImageVector,
    val iconContentDescription: String
)

val bottomNavigationItems = listOf(
    BottomNavigationItem(
        Screen.TimeZonesScreen.title,
        Icons.Filled.Language,
        "Timezones"
    ),
    BottomNavigationItem(
        Screen.FindTimeScreen.title,
        Icons.Filled.Place,
        "Find Time"
    )
)

// 1
@Composable
// 2
fun MainView(actionBarFun: topBarFun = { emptyComposable() }) {
    // 3
    val showAddDialog = remember { mutableStateOf(false) }
    // 4
    val currentTimezoneStrings = remember { SnapshotStateList<String>() }
    // 5
    val selectedIndex = remember { mutableStateOf(0)}
    // 6
    AppTheme {
        Scaffold(
            topBar = {
                actionBarFun(selectedIndex.value)
            },
            floatingActionButton = {
                if (selectedIndex.value == 0) {
                    // 1
                    FloatingActionButton(
                        // 2
                        modifier = Modifier
                            .padding(16.dp),
                        // 3
                        onClick = {
                            showAddDialog.value = true
                        }
                    ) {
                        // 4
                        Icon(
                            imageVector = Icons.Default.Add,
                            contentDescription = null
                        )
                    }
                }
            },
            bottomBar = {
                // 1
                BottomNavigation {
                    // 2
                    bottomNavigationItems.forEachIndexed { i, bottomNavigationItem ->
                        // 3
                        BottomNavigationItem(
                            // 4
                            icon = {
                                Icon(
                                    bottomNavigationItem.icon,
                                    contentDescription = bottomNavigationItem.iconContentDescription
                                )
                            },
                            // 5
                            selected = selectedIndex.value == i,
                            // 6
                            onClick = {
                                selectedIndex.value = i
                            }
                        )
                    }
                }
            }
        ) {
            // TODO: Replace with Dialog
            // TODO: Replace with screens
        }
    }
}