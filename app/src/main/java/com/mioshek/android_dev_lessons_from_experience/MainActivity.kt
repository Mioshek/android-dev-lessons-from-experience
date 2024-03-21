package com.mioshek.android_dev_lessons_from_experience

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat
import com.mioshek.android_dev_lessons_from_experience.ui.theme.AndroiddevlessonsfromexperienceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroiddevlessonsfromexperienceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    MainMenu()
                }
            }
        }
    }
}

@Composable
fun MainMenu(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val scrollState = rememberScrollState(0)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .horizontalScroll(scrollState)
    ) {
        for (lesson in LessonsEnum.entries){
            Button(
                onClick = {
                    val intent = Intent(context, lesson.activity.java)
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                    ContextCompat.startActivity(context, intent, null)
                }
            ) {
                Text(text = lesson.name)
            }
        }
    }
}

@Preview(showBackground = true, showSystemUi = true)
@Composable
fun GreetingPreview() {
    AndroiddevlessonsfromexperienceTheme {
        MainMenu()
    }
}