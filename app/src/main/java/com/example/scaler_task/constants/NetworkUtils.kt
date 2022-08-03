package com.example.scaler_task.constants

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.telephony.TelephonyManager
import android.util.Log

object NetworkUtils {
    fun getNetworkInfo(context: Context): NetworkInfo? {
        try {
            val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            return cm.activeNetworkInfo
        } catch (e: Exception) {
            Log.e("zzz", "exception in getNetworkInfo", e)
        }
        return null
    }

    fun isConnected(info: NetworkInfo?): Boolean {
        return info != null && info.isConnected
    }

    fun isConnected(context: Context): Boolean {
        return isConnected(getNetworkInfo(context))
    }

    fun isNetworkAvailable(context: Context): Boolean {
        return isConnected(getNetworkInfo(context))
    }

    fun isConnectedWifi(info: NetworkInfo?): Boolean {
        return isConnected(info) && info!!.type == ConnectivityManager.TYPE_WIFI
    }

    fun isConnectedWifi(context: Context): Boolean {
        return isConnectedWifi(getNetworkInfo(context))
    }

    fun isConnectedMobile(info: NetworkInfo?): Boolean {
        return isConnected(info) && info!!.type == ConnectivityManager.TYPE_MOBILE
    }

    fun isConnectedMobile(context: Context): Boolean {
        return isConnectedMobile(getNetworkInfo(context))
    }

    fun isConnectedFast(info: NetworkInfo?): Boolean {
        return isConnected(info) && isConnectionFast(info!!.type, info.subtype)
    }

    fun isConnectedFast(context: Context): Boolean {
        return isConnectedFast(getNetworkInfo(context))
    }

    fun getNetworkClass(info: NetworkInfo?): String? {
        if (!isConnected(info)) {
            return null
        }
        if (isConnectedWifi(info)) {
            return "WIFI"
        }
        val networkType = info!!.subtype
        return when (networkType) {
            TelephonyManager.NETWORK_TYPE_GPRS, 16, TelephonyManager.NETWORK_TYPE_EDGE, TelephonyManager.NETWORK_TYPE_CDMA, TelephonyManager.NETWORK_TYPE_1xRTT, TelephonyManager.NETWORK_TYPE_IDEN -> "2G"
            TelephonyManager.NETWORK_TYPE_UMTS, TelephonyManager.NETWORK_TYPE_EVDO_0, TelephonyManager.NETWORK_TYPE_EVDO_A, TelephonyManager.NETWORK_TYPE_HSDPA, TelephonyManager.NETWORK_TYPE_HSUPA, TelephonyManager.NETWORK_TYPE_HSPA, TelephonyManager.NETWORK_TYPE_EVDO_B, TelephonyManager.NETWORK_TYPE_EHRPD, TelephonyManager.NETWORK_TYPE_HSPAP, 17 -> "3G"
            TelephonyManager.NETWORK_TYPE_LTE, 18, 19 -> "4G"
            else -> "Unknown"
        }
    }

    private fun isConnectionFast(type: Int, subType: Int): Boolean {
        return if (type == ConnectivityManager.TYPE_WIFI) {
            true
        } else if (type == ConnectivityManager.TYPE_MOBILE) {
            when (subType) {
                TelephonyManager.NETWORK_TYPE_GPRS, TelephonyManager.NETWORK_TYPE_EDGE, TelephonyManager.NETWORK_TYPE_CDMA, TelephonyManager.NETWORK_TYPE_1xRTT, TelephonyManager.NETWORK_TYPE_IDEN, 16 -> false
                TelephonyManager.NETWORK_TYPE_UMTS, TelephonyManager.NETWORK_TYPE_EVDO_0, TelephonyManager.NETWORK_TYPE_EVDO_A, TelephonyManager.NETWORK_TYPE_HSDPA, TelephonyManager.NETWORK_TYPE_HSUPA, TelephonyManager.NETWORK_TYPE_HSPA, TelephonyManager.NETWORK_TYPE_EVDO_B, TelephonyManager.NETWORK_TYPE_LTE, TelephonyManager.NETWORK_TYPE_EHRPD, TelephonyManager.NETWORK_TYPE_HSPAP, 17, 18, 19 -> true
                TelephonyManager.NETWORK_TYPE_UNKNOWN -> false
                else -> false
            }
        } else {
            false
        }
    }

    fun getNetworkClass(context: Context): String? {
        return getNetworkClass(getNetworkInfo(context))
    }
}