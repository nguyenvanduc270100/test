package com.bangcodin.alldocumentreader.utils

import android.content.Context
import android.content.SharedPreferences

class SharePreference {

    companion object {
        private const val PREF_NAME: String = "AllDocumentReader"
        const val PRIVATE_MODE: Int = 0
        const val IS_FIRST_LAUNCH = "first_launch"
        const val SELECTED_LANGUAGE = "selected_language"
        const val VIEW_MODE = "view_mode"
        const val SORT_TYPE = "sort_type"
        fun getIntPref(context: Context, key: String): Int {
            val pref: SharedPreferences =
                context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            return pref.getInt(key, -1)
        }

        fun setIntPref(context: Context, key: String, value: Int) {
            val pref: SharedPreferences =
                context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            pref.edit().putInt(key, value).apply()
        }

        fun getStringPref(context: Context, key: String): String? {
            val pref: SharedPreferences =
                context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            return pref.getString(key, "")
        }

        fun setStringPref(context: Context, key: String, value: String) {
            val pref: SharedPreferences =
                context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            pref.edit().putString(key, value).apply()
        }

        fun getBooleanPref(context: Context, key: String): Boolean {
            val pref: SharedPreferences =
                context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            return pref.getBoolean(key, false)
        }

        fun setBooleanPref(context: Context, key: String, value: Boolean) {
            val pref: SharedPreferences =
                context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
            pref.edit().putBoolean(key, value).apply()
        }
    }

}