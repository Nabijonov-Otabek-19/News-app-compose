package uz.gita.newsappcompose.utils

import android.content.Context
import android.util.Log
import android.widget.Toast

fun logger(msg: String) {
    Log.d("AAA", msg)
}

fun toast(context: Context, message: String) {
    Toast.makeText(context, message, Toast.LENGTH_SHORT).show()
}