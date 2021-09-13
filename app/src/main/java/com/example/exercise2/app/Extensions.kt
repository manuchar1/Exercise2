package com.example.exercise2.app


import androidx.fragment.app.Fragment
import com.example.exercise2.utils.Resource
import com.google.android.material.snackbar.Snackbar


fun Fragment.snackbar(text: String) {
    Snackbar.make(requireView(), text, Snackbar.LENGTH_LONG).show()
}

inline fun <T> safeCall(action: () -> Resource<T>): Resource<T> {

    return try {
        action()
    } catch (e: Exception) {
        Resource.Error(e.message ?: "An unknown error occured")
    }
}



