package com.mioshek.android_dev_lessons_from_experience.lessons.permissions

enum class CustomPermission(val permission: String){
    CALENDAR(android.Manifest.permission.READ_CALENDAR),
    CAMERA(android.Manifest.permission.CAMERA),
    CONTACTS(android.Manifest.permission.READ_CONTACTS),
    LOCATION(android.Manifest.permission.ACCESS_FINE_LOCATION),
    MICROPHONE(android.Manifest.permission.RECORD_AUDIO),
    PHONE(android.Manifest.permission.CALL_PHONE),
    PHONECALLS(android.Manifest.permission.ANSWER_PHONE_CALLS),
    BODYSENSORS(android.Manifest.permission.BODY_SENSORS),
    DATA(android.Manifest.permission.READ_EXTERNAL_STORAGE),
    SMS(android.Manifest.permission.READ_SMS),
    MESSAGES(android.Manifest.permission.SEND_SMS),
    STORAGE(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
}