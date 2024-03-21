package com.mioshek.android_dev_lessons_from_experience

import com.mioshek.android_dev_lessons_from_experience.lessons.FileOpener
import com.mioshek.android_dev_lessons_from_experience.lessons.permissions.Permissions
import kotlin.reflect.KClass

enum class LessonsEnum(val activity: KClass<*>) { // There is probably better way
    PERMISSIONS(Permissions::class),
    SAVING_FILES(FileOpener::class)
}