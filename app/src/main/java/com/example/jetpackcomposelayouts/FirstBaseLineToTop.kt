package com.example.jetpackcomposelayouts

import androidx.compose.foundation.background
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.FirstBaseline
import androidx.compose.ui.layout.layout
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.jetpackcomposelayouts.ui.theme.JetpackComposeLayoutsTheme

fun Modifier.firstBaseLineToTop(
    firstBaseLineToTop: Dp
) = this.then(
    layout { measurable, constraints ->
        //測量元素
        val placeable = measurable.measure(constraints)
        //測量後，獲取元素的baseline
        val firstLine = placeable[FirstBaseline]
        //元素新的Y座標 = new baseline - old baseline
        val placeableY = firstBaseLineToTop.roundToPx() - firstLine
        val height = placeable.height + placeableY
        layout(placeable.width, height) {
            //設置元素位置
            placeable.place(0, placeableY)
        }
    }
)

@Preview(showBackground = true)
@Composable
fun TextWithPaddingToBaseLine() {
    JetpackComposeLayoutsTheme {
        Text(
            text = "Hi there!",
            Modifier
                .firstBaseLineToTop(30.dp)
                .background(Color.Red)
        )
    }
}