package com.example.lemonade


import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                LemonadeApp()
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadeAppPreview() {
    LemonadeApp()
}

@Composable
fun LemonadeApp() {
    var currentStep by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }
    when (currentStep) {
        1 -> {
            LemonTextAndImage(
                text = R.string.lemon_tree_text,
                figure = R.drawable.lemon_tree,
                imageContentDescription = R.string.content_description_step1,
                onImageClick = {
                    currentStep = 2
                    squeezeCount = (2..4).random()
                }
            )
        }
        2 -> {
            LemonTextAndImage(
                text = R.string.lemon_squeeze_text,
                figure = R.drawable.lemon_squeeze,
                imageContentDescription = R.string.content_description_step2,
                onImageClick = {
                    squeezeCount--
                    if (squeezeCount == 0) {
                        currentStep = 3
                    }
                }
            )
        }
        3 -> {
            LemonTextAndImage(
                text = R.string.lemon_drink_text,
                figure = R.drawable.lemon_drink,
                imageContentDescription = R.string.content_description_step3,
                onImageClick = {
                    currentStep = 4
                }
            )
        }
        4 -> {
            LemonTextAndImage(
                text = R.string.lemon_restart_text,
                figure = R.drawable.lemon_restart,
                imageContentDescription = R.string.content_description_step4,
                onImageClick = {
                    currentStep = 1
                }
            )
        }
    }
}

@Composable
fun LemonTextAndImage(
    text: Int,
    figure: Int,
    imageContentDescription: Int,
    onImageClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(
            onClick = onImageClick,
            shape = RoundedCornerShape(40.dp),
        ) {
            Image(
                painter = painterResource(figure),
                contentDescription = stringResource(id = imageContentDescription),
                modifier = Modifier
                    .height(235.dp)
                    .width(195.dp)
                    .padding(20.dp)
            )
        }
        Spacer(modifier = Modifier.height(26.dp))
        Text(text = stringResource(text),
            fontSize = 13.sp
        )
    }
}