package com.example.jetpackcomposelayouts

import android.util.Log
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.AlignmentLine
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.layout.Placeable
import androidx.compose.ui.tooling.preview.Preview
import com.example.jetpackcomposelayouts.ui.theme.JetpackComposeLayoutsTheme

@Composable
fun MyOwnColumn(
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit
) {
    /**自定義Column,content就是傳進來的Compose View，在這個範例傳的是Text()
     * Layout是最上層的View所以來造一個View，給他modifier和傳進來的Text()
     * */
    Layout(modifier = modifier, content = content) { measurables, constrains ->
        val placeables: List<Placeable> = measurables.map {
            it.measure(constrains)
        }
        var yPosition = 0

        /**當前佈局的大小 constrains可以得到當前的父類別大小
         * 然後透過 layout{} 去重劃一個View，constrains可以得到父View的寬高
         * */
        layout(constrains.maxWidth, constrains.maxHeight) {
            placeables.forEach { placeable ->
                placeable.placeRelative(0, yPosition)
                /**因為每次傳進來的Text都有高度，所以放置玩一個Text之後放下一個就是前一個的高度加上去
                 * 如果高度沒有增加的話他會所有Text()重疊
                 * */
                yPosition += placeable.height
            }
        }
    }
}

@Preview()
@Composable
fun MyOwnColumnSimple() {
    JetpackComposeLayoutsTheme {
        MyOwnColumn {
            Text(text = "11111111")
            Text(text = "22222222")
            Text(text = "33333333")
            Text(text = "44444444")
        }
    }
}