package com.example.jetpackcomposelayouts

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Preview()
@Composable
fun PhotographicCard(modifier: Modifier = Modifier) {
    Row(
        modifier = Modifier
            .clip(RoundedCornerShape(4.dp))
            .background(MaterialTheme.colors.surface)
            /**如果事先padding()再clickable() 填充的padding()那一塊點擊是沒有效果的*/
            //.padding(16.dp).clickable(onClick = {})
            .clickable(onClick = {}).padding(16.dp)
    ) {
        Surface(
            modifier = Modifier.size(50.dp),
            shape = CircleShape,
            /** 例如 Theme裡面有 primary 就會有 onPrimary 透過copy()將數值拷貝出來使用*/
            color = MaterialTheme.colors.onSurface.copy(alpha = 0.2f)
        ) {
            Image(
                painter = painterResource(id = R.drawable.cat),
                contentDescription = "Cat"
            )
        }

        Column(
            modifier = Modifier
                .padding(start = 8.dp)
                .align(Alignment.CenterVertically)
        ) {
            Text(text = "Test", fontWeight = FontWeight.Bold)

            /**
             * CompositionLocalProvider 可以直接從 內建的資源拿到數值 直接套用到括號包含起來的View
             * 也可以透過 LocalContentAlpha.provides(ContentAlpha.medium) 取得值*/
            CompositionLocalProvider(LocalContentAlpha provides ContentAlpha.medium) {
                Text(text = "ghfuidsghufidhsgiu", style = MaterialTheme.typography.body2)
            }
        }
    }
}