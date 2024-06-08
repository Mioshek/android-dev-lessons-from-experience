package com.mioshek.android_dev_lessons_from_experience.lessons.datasharing

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat
import com.mioshek.android_dev_lessons_from_experience.ui.theme.AndroidDevLessonsFromExperienceTheme
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.runBlocking
import kotlin.reflect.KClass
import java.util.concurrent.ConcurrentHashMap


/**
 * Testing [ConcurrentHashMap] with multiple activities
 */
class DataSharing : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroidDevLessonsFromExperienceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    DataSharingView()
                }
            }
        }
    }

    override fun onActivityResult(
        requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)
    }

}

enum class Activities(val activity: KClass<*>){
    ACTIVITYONE(ActivityOne::class),
    ACTIVITYTWO(ActivityTwo::class),
    ACTIVITYTHREE(ActivityThree::class)
}

@Composable
fun DataSharingView(
    modifier: Modifier = Modifier
){
    val context = LocalContext.current
    val coroutineScope = rememberCoroutineScope()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Button(onClick = {
            Activities.entries.forEach {activity ->
                coroutineScope.run {
                    val intent = Intent(context, activity.activity.java)
                    ContextCompat.startActivity(context, intent, null)
                }
            }
        }) {
            Text(text = "Start All Activities")
        }
    }
}