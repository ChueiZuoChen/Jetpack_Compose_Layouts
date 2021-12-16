package com.example.jetpackcomposelayouts

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

/**Scaffold可以修改各種MaterialDesign組件,例如Floating ActionButton, 左右滑動抽屜清單Drawer, 頂部的topBar...*/
@Preview()
@Composable
fun LayoutStudy() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "LayoutStudy")
                },
                actions = {
                    IconButton(onClick = {}) {
                        Icon(imageVector = Icons.Filled.Favorite, contentDescription = null)
                    }
                }
            )
        }
    ) { innerPadding ->
        BodyContent(
            Modifier.padding(innerPadding)
        )
    }
}

@Composable
fun BodyContent(modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier.padding(8.dp)
    ) {
        Text(text = "AndroidStudio")
        Text(text = "KotlinAndroid")
    }
}