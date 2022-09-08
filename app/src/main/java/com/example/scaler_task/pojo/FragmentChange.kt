package com.example.scaler_task.pojo

import android.os.Bundle
import androidx.fragment.app.Fragment

data class FragmentChange(
    val T: Class<out Fragment?>, val tag: String,
    val args: Bundle? = null, val addToBackStack: Boolean = false, val startTransition: Int = 0,
    val endTransition: Int = 0,
    val popEnterTransition: Int = 0,
    val popExitTransition:Int = 0,
    val shouldRemove: Boolean = false
) {
}