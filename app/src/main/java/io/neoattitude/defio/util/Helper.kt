package io.neoattitude.defio.util

import android.view.View
import com.google.android.material.snackbar.Snackbar

object Helper {
    fun View.snack(message: String, duration: Int = Snackbar.LENGTH_LONG) {
        Snackbar.make(this, message, duration).show()
    }
}