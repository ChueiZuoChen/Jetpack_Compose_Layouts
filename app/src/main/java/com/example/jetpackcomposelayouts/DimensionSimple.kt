package com.example.jetpackcomposelayouts

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.constraintlayout.compose.ConstraintLayout

@Preview(showBackground = true)
@Composable
fun BreakLine() {
    ConstraintLayout(modifier = Modifier.fillMaxSize()) {
        val guideline = createGuidelineFromStart(0.5f)
        val text = createRef()
        Text(
            text = "ghfuidshgiufds\nngfnds321312aaaaaaaaaa31231231231gifdisgnifdsn hgfui nshgiufdsn giufdn giufdgiu fdhius gdfius giuofdsnh giufdosngiu ofdnh uigfd n",
            modifier = Modifier
                .constrainAs(text) {
                    start.linkTo(guideline)
                    top.linkTo(parent.top)

                },
            overflow = TextOverflow.Visible,
            softWrap = true
        )

    }
}