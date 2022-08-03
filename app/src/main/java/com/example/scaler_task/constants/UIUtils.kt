package com.example.scaler_task.constants

import android.content.Context
import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

object UIUtils {
    fun isNetworkAvailable(context: Context?): Boolean {
        return NetworkUtils.isConnected(context!!)
    }


    fun addFragment(
        context: AppCompatActivity,
        @IdRes containerViewId: Int,
        @NonNull fragment: Fragment,
        @NonNull fragmentTag: String?
    ) {
        context.getSupportFragmentManager()
            .beginTransaction()
            .add(containerViewId, fragment, fragmentTag)
            .addToBackStack(fragmentTag)
            .commitAllowingStateLoss()
    }
}