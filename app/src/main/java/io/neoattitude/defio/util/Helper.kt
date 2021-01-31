package io.neoattitude.defio.util

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import io.neoattitude.defio.R
object Helper {
    fun View.snack(message: String, duration: Int = Snackbar.LENGTH_LONG) {
        Snackbar.make(this, message, duration).show()
    }

    fun decorator(context: Context): RecyclerView.ItemDecoration {
        val decorator = DividerItemDecoration(context, LinearLayoutManager.VERTICAL)
        decorator.setDrawable(ContextCompat.getDrawable(context, R.drawable.divider)!!)

        return decorator
    }

}

