package com.example.jetpackcomposelayouts

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.Layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.math.max

val topics = listOf<String>(
    "Android",
    "Kotlin",
    "Java",
    "C++",
    "C#",
    "ASP.ner",
    "Flutter",
    "Swift",
    "IOS",
    "PHP",
    "HTML",
    "CSS"
)

@Preview
@Composable
fun Test() {
    StaggledGridBodyContent()
}

@Composable
fun StaggledGridBodyContent(
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .background(Color.LightGray)
            .padding(16.dp)
            .horizontalScroll(rememberScrollState()),
        content = {
            StaggledGrid(modifier = Modifier) {
                for (topic in topics) {
                    Chip(Modifier.padding(8.dp), text = topic)
                }
            }
        }
    )
}

@Composable
fun StaggledGrid(
    modifier: Modifier = Modifier,
    rows: Int = 3,
    content: @Composable () -> Unit
) {

    Layout(modifier = modifier, content = content) { measurables, constrains ->
        //用來存放每行的寬度與高度
        val rowWidths = IntArray(rows) { 0 }
        val rowHeights = IntArray(rows) { 0 }

        //測量每個進來的View
        val placeables = measurables.mapIndexed { index, measurable ->
            val placeable = measurable.measure(constrains)

            /**
             * 計算寬度與高度
             * 假設有11元素 0,1,2,3,4,5,6,7,8,9,10
             * rows = 3 一行有三個
             * 橫向排列寬度就是 0,1,2
             * 高度就是 index % rows 也就是說 0,3,6的高度加起來就是 父View的高度
             * */
            val row = index % rows
            /**一行的最寬寬度之和*/
            rowWidths[row] += placeable.width
            /**一行的最高的高度之和*/
            rowHeights[row] = max(rowWidths[row], placeable.height)
            placeable
        }

        /**計算表格的寬度，應該是當中最寬的一行*/
        val width = rowWidths.maxOrNull() ?: constrains.minWidth

        /**表格高度應該是所有高度之和*/
        val height = rowHeights.sumOf { it }

        /**設置每一行的Ｙ座標
         * 因為第一行的座標是0所以從1開始
         * */
        val rowY = IntArray(rows) { 0 }
        for (i in 1 until rows) {
//            rowY[i] = rowY[0]+rowHeights[0]  第一行的座標就是第0行的Ｙ座標＋第0行的高
            rowY[i] = rowY[i - 1] + rowHeights[i - 1]
        }


        layout(width, height) {
            val rowX = IntArray(rows) { 0 }
            //放置每個View的座標
            placeables.forEachIndexed { index, placeable ->
                //index: 0,1,2,3,4,5,6,7,8,9,10
                //rows: 3
                //row:0,1,2
                val row = index % rows
                placeable.placeRelative(x = rowX[row], y = rowY[index])

                /**設置X 下一個寬度*/
                rowX[row] += placeable.width
            }
        }

    }
}

@Composable
fun Chip(
    modifier: Modifier = Modifier,
    text: String
) {
    /**一個卡片，圓角，裡面包含一個Row，第一列是Box，第二列文字*/
    Card(
        modifier = modifier,
        border = BorderStroke(color = Color.Black, width = Dp.Hairline),
        shape = RoundedCornerShape(8.dp)
    ) {
        Row(
            modifier = Modifier.padding(start = 8.dp, top = 4.dp, end = 8.dp, bottom = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(16.dp, 16.dp)
                    .background(MaterialTheme.colors.secondary)
            )
            Spacer(modifier = Modifier.width(4.dp))
            Text(text = text)
        }
    }
}