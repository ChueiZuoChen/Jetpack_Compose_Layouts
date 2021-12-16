package com.example.jetpackcomposelayouts

import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.layoutId
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.compose.ConstraintSet

@Preview(showBackground = true)
@Composable
fun MyLayout() {

    BoxWithConstraints(modifier = Modifier.fillMaxSize(1f)) {
        val constrains = if (maxWidth < maxHeight) {
            decoupleMyLayout(margin = 16.dp)
        } else {
            decoupleMyLayout(margin = 160.dp)
        }
        ConstraintLayout(constrains) {
            Button(
                onClick = {},
                modifier = Modifier.layoutId("button")
            ) {
                Text(text = "Button")
            }
            Text(
                text = "Text",
                modifier = Modifier.layoutId("text")
            )
        }
    }
}

fun decoupleMyLayout(margin: Dp): ConstraintSet {
    return ConstraintSet {
        val button = createRefFor("button")
        val text = createRefFor("text")
        constrain(button) {
            top.linkTo(parent.top, margin = margin)
            start.linkTo(parent.start, margin = margin)
        }
        constrain(text) {
            end.linkTo(parent.end, margin = margin)
            bottom.linkTo(parent.bottom, margin = margin)
        }
    }
}
