package Util

import android.content.Context
import android.content.Intent

class Util {
    fun OpenActivity(context: Context, activity: Class<*>) {
        val intent = Intent(context, activity)
        context.startActivity(intent)
    }
}