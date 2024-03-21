package com.mioshek.android_dev_lessons_from_experience.lessons

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.net.toUri
import com.mioshek.android_dev_lessons_from_experience.ui.theme.AndroiddevlessonsfromexperienceTheme
import java.io.FileNotFoundException
import java.io.FileOutputStream
import java.io.IOException

enum class CustomOption{
    EXPORT,
    IMPORT
}

class FileOpener : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            AndroiddevlessonsfromexperienceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    FileOpenerScreen()
                }
            }
        }
    }

    override fun onActivityResult(
        requestCode: Int, resultCode: Int, resultData: Intent?) {
        super.onActivityResult(requestCode, resultCode, resultData)
        if (requestCode == 1
            && resultCode == Activity.RESULT_OK) {
            // The result data contains a URI for the document or directory that
            // the user selected.
            resultData?.data?.also { uri ->
                insertDataIntoFile(uri, applicationContext, "Hejka")
            }
        }
    }

}

@Composable
fun FileOpenerScreen(modifier: Modifier = Modifier) {
    var textToSave by remember{mutableStateOf("")}
    val context = LocalContext.current
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp)
    ) {
        TextField(
            value = textToSave,
            onValueChange = {
                textToSave = it
            }
        )
        Button(
            onClick = {
                val a = Intent(context, FileOpener::class.java).putExtra("Option", CustomOption.EXPORT)
                a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                ContextCompat.startActivity(context, a, Bundle())
            }
        ) {
            Text(text = "Export")
        }

        Button(
            onClick = {
                val a = Intent(context, FileOpener::class.java).putExtra("Option", CustomOption.IMPORT)
                a.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                ContextCompat.startActivity(context, a, Bundle())
            }
        ) {
            Text(text = "Import")
        }
    }
}

private fun createFile(context: Context, sbody: String) {
    val activity = context as Activity
    val intent = Intent(Intent.ACTION_CREATE_DOCUMENT).apply {
        setDataAndType(Environment.getExternalStorageDirectory().path.toUri(), "text/plain")
        addCategory(Intent.CATEGORY_OPENABLE)
        putExtra(Intent.EXTRA_TITLE, "ExportHabits.txt")
    }
    ActivityCompat.startActivityForResult(activity, intent, 1, null)
}

private fun insertDataIntoFile(uri: Uri, context: Context, sbody: String){
    try {
        context.contentResolver.openFileDescriptor(uri, "w")?.use {
            FileOutputStream(it.fileDescriptor).use {
                it.write(
                    ("Overwritten at ${System.currentTimeMillis()}\n")
                        .toByteArray()
                )
            }
        }
    } catch (e: FileNotFoundException) {
        e.printStackTrace()
    } catch (e: IOException) {
        e.printStackTrace()
    }
}

private fun openFile(activity: Activity) {
    val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
        addCategory(Intent.CATEGORY_OPENABLE)
        type = "application/zip"

        // Optionally, specify a URI for the file that should appear in the
        // system file picker when it loads.
    }

    ActivityCompat.startActivityForResult(activity, intent, 2, Bundle())
    Log.d("Data", intent.extras.toString())
}