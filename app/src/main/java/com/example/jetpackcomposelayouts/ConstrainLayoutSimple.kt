package com.example.jetpackcomposelayouts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import androidx.constraintlayout.widget.Guideline

/**
 * 約束佈局 ConstrainLayout
 * 引用是使用 createRefs() 或是 createRefFor() 創建的
 * 約束是使用 constrainAs()修飾符 可以在主體Lambda中指定其約束對象
 * 約束條件是使用 linkTo() 或其他有用的方法指定得
 * */

@Preview
@Composable
fun MyContent() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val (button, text, button2, text2, middle) = createRefs()
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.constrainAs(middle) {
                start.linkTo(parent.start, margin = 8.dp)
                top.linkTo(parent.top, margin = 8.dp)
                end.linkTo(parent.end, margin = 8.dp)
                bottom.linkTo(parent.bottom, margin = 8.dp)
            }
        ) {
            Text(text = "Middle Button")

        }
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.constrainAs(button) {
                start.linkTo(parent.start, margin = 8.dp)
                top.linkTo(parent.top, margin = 16.dp)
                end.linkTo(parent.end, margin = 8.dp)
            }
        ) {
            Text(text = "Button1")

        }
        Button(
            onClick = { /*TODO*/ },
            modifier = Modifier.constrainAs(button2) {
                start.linkTo(parent.start, margin = 8.dp)
                end.linkTo(parent.end, margin = 8.dp)
                bottom.linkTo(parent.bottom, margin = 16.dp)
            }
        ) {
            Text(text = "Button2")

        }
        val guideline = createGuidelineFromTop(0.5f)
        Text(text = "Hello", modifier = Modifier.constrainAs(text) {
            top.linkTo(button.bottom, margin = 4.dp)
            start.linkTo(parent.start, margin = 8.dp)
            end.linkTo(parent.end, margin = 8.dp)
            bottom.linkTo(guideline)
        })
        Text(text = "Hello2", modifier = Modifier.constrainAs(text2) {
            top.linkTo(guideline)
            start.linkTo(parent.start, margin = 8.dp)
            end.linkTo(parent.end, margin = 8.dp)
            bottom.linkTo(button2.top, margin = 4.dp)
        })
    }
}