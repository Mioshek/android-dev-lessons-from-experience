package com.mioshek.android_dev_lessons_from_experience.lessons.permissions

import android.app.Activity
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat.requestPermissions
import com.mioshek.android_dev_lessons_from_experience.ui.theme.AndroiddevlessonsfromexperienceTheme


class Permissions : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroiddevlessonsfromexperienceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    PermissionsScreen()
                }
            }
        }
    }
}

@Composable
fun PermissionsScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(start = 20.dp, end = 20.dp)
    ) {
        for (permission in CustomPermission.entries){
            PermissionCard(permission = permission)
        }
    }
}

@Composable
fun PermissionCard(permission: CustomPermission){
    val context = LocalContext.current
    Button(onClick = {
        requestPermissions(
            context as Activity,
            arrayOf(permission.permission),
            1
        )
    }) {
        Text(text = permission.permission)
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    AndroiddevlessonsfromexperienceTheme {
        PermissionsScreen()
    }
}