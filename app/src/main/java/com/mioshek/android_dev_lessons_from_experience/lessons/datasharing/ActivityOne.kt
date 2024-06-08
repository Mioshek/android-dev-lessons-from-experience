package com.mioshek.android_dev_lessons_from_experience.lessons.datasharing

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.mioshek.android_dev_lessons_from_experience.ui.theme.AndroidDevLessonsFromExperienceTheme

class ActivityOne : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d("ActivityOne","Activity 1 Running")
        val taken = Storage.take<String>("test")
        Log.d("ValueTaken", taken)
    }
}