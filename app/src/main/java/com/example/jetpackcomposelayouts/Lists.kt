package com.example.jetpackcomposelayouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import kotlinx.coroutines.launch


@Composable
fun SimpleColumn() {
    Column {
        repeat(100) {
            Text(
                text = "Item $it",
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}

/**因為Column預設不能去滑動,所以要透過rememberScrollState() 去改動
 * State原理： 因為畫面上透過滑動點擊等等的操作會讓狀態改變，當狀態一改變之後 @Composable對象的圖就會去重組(重繪)
 * Composable函數只處理畫面的繪圖，他不處理狀態的改變(狀態的改變重組是AndroidJetpackCompose底層完成的)
 * 當Compose組件接收了狀態，他就會變成隨著狀態改變而重組
 * */
@Composable
fun SimpleList() {
    val scrollState = rememberScrollState()
    Column(
        modifier = Modifier.verticalScroll(scrollState)
    ) {
        repeat(100) {
            Text(
                text = "Item $it",
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}

/**LazyColumn,LazyRow就像是Recyclerview或是Paging自帶緩衝預讀*/
@Composable
fun LazyList() {
    val scrollState = rememberLazyListState()
    LazyColumn(state = scrollState) {
        items(count = 100) {
            Text(
                text = "Item $it",
                style = MaterialTheme.typography.subtitle1
            )
        }
    }
}

@Composable
fun ScrollingList() {
    val scrollState = rememberLazyListState()
    val listSize = 100
    val coroutineScope = rememberCoroutineScope()
    Column {
        Row {
            Button(
                /**權重一樣，兩個button各佔一邊*/
                modifier = Modifier.weight(1f),
                onClick = {
                    coroutineScope.launch {
                        scrollState.animateScrollToItem(0)
                    }
                },
            ) {
                Text(text = "Scroll to the top")
            }
            Button(
                modifier = Modifier.weight(1f),
                onClick = {
                    coroutineScope.launch {
                        scrollState.animateScrollToItem(listSize-1)
                    }
                }
            ) {
                Text(text = "Scroll to the end")
            }
        }

        LazyColumn(state = scrollState) {
            items(count = listSize) {
//                Text(
//                    text = "Item $it",
//                    style = MaterialTheme.typography.subtitle1
//                )
                ImageListItem(index = it)
            }
        }
    }
}

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ImageListItem(index: Int) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Image(
            /** coil 庫*/
            painter = rememberImagePainter(
                data = "http://cdn3.iconfinder.com/data/icons/logos-brands-3/24/logo_brand_brands_logos_android-1024.png"
            ),
            modifier = Modifier.size(50.dp),
            contentDescription = null
        )
        Spacer(modifier = Modifier.size(10.dp))
        Text(text = "Item #$index", style = MaterialTheme.typography.subtitle1)
    }

}