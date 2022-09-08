package com.example.scaler_task.constants

import android.content.Context
import android.os.Bundle
import androidx.annotation.IdRes
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager

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

    fun replaceFragment(
        context: AppCompatActivity,
        fragmentManager: FragmentManager?,
        T: Class<out Fragment?>,
        arguments: Bundle? = null,
        TAG: String?,
        containerViewId: Int,
        addToBackStack: Boolean,
        startTransition: Int = 0,
        endTransition: Int = 0,
        popEnterTransition: Int = 0,
        popExitTransition:Int = 0,
        addInsteadReplace: Boolean = false
    ) {
        var fragmentManager = fragmentManager
        if (fragmentManager == null) {
            fragmentManager = context.supportFragmentManager
        }
        var fragment = fragmentManager.findFragmentByTag(TAG)
        if (fragment == null) {
            try {
                fragment = T.newInstance()
                fragment!!.arguments = arguments
            } catch (e: InstantiationException) {
            } catch (e: IllegalAccessException) {
            }
        }
        val fragmentTransaction = fragmentManager.beginTransaction()
        fragmentTransaction.setCustomAnimations(startTransition, endTransition, popEnterTransition, popExitTransition)
        if (addInsteadReplace) {
            fragmentTransaction.add(containerViewId, fragment!!, TAG)
        } else {
            fragmentTransaction.replace(containerViewId, fragment!!, TAG)
        }
        if (addToBackStack) {
            fragmentTransaction.addToBackStack(TAG)
        }
        fragmentTransaction.commitAllowingStateLoss()
    }

    fun removeFragmentsByTag(context: AppCompatActivity?, vararg fragmentTags: String?): Boolean {
        var isAnyFragmentRemoved = false
        if (context != null) {
            val fragmentManager = context.supportFragmentManager
            for (tag in fragmentTags) {
                val fragment = fragmentManager.findFragmentByTag(tag)
                if (fragment != null && !fragment.isRemoving) {
                    isAnyFragmentRemoved = true
                    fragmentManager.beginTransaction().remove(fragment).commitAllowingStateLoss()
                }
            }
        }
        return isAnyFragmentRemoved
    }
}