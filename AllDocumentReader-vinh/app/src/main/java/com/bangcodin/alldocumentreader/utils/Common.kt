package com.bangcodin.alldocumentreader.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.core.content.getSystemService
import com.bangcodin.alldocumentreader.R
import com.shashank.sony.fancytoastlib.FancyToast
import java.text.SimpleDateFormat
import java.util.*

object Common {


    fun showErrorFullMsg(activity: Activity, message: String) {
        FancyToast.makeText(activity, message, FancyToast.LENGTH_SHORT, FancyToast.WARNING, false)
            .show()
    }
    fun closeKeyBoard(activity: Activity) {
        val view: View? = activity.currentFocus
        if (view != null) {
            try {
                val imm: InputMethodManager =
                    activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(view.windowToken, 0)
            } catch (e: java.lang.Exception) {
                e.printStackTrace()
            }
        }
    }
    fun openKeyBoard(activity: Activity){
        val view: View? = activity.currentFocus
        if(view!= null){
            try {
                val imm:InputMethodManager = activity.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.showSoftInput(view,InputMethodManager.SHOW_IMPLICIT)
            }
            catch (e: java.lang.Exception){
                e.printStackTrace()
            }
        }
    }

    //"2021-10-26 05:05:56"
    fun getDateTime(strDate: String): String? {
        val curFormater = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US)
        val dateObj = curFormater.parse(strDate)
        val postFormater = SimpleDateFormat("dd MMM yyyy hh:mm a", Locale.US)
        return dateObj?.let { postFormater.format(it) }
    }

    fun shareUs(activity: Activity) {
        val intent = Intent("android.intent.action.SEND")
        intent.type = "text/plain"
        intent.putExtra(
            "android.intent.extra.SUBJECT",
            activity.resources.getString(R.string.app_name)
        )
        val sb = StringBuilder()
        sb.append("Download this amazing ")
        sb.append(activity.resources.getString(R.string.app_name))
        sb.append(" app from play store\n\n")
        val sb2 = sb.toString()
        val sb3 = StringBuilder()
        sb3.append(sb2)
        sb3.append("https://play.google.com/store/apps/details?id=")
        sb3.append(activity.packageName)
        sb3.append("\n\n")
        intent.putExtra("android.intent.extra.TEXT", sb3.toString())
        activity.startActivity(Intent.createChooser(intent, "Choose one"))
    }

    fun goToPrivacyPage(activity: Activity) {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse(AppConfig.PRIVACY_LINK)
        activity.startActivity(intent)
    }

    @SuppressLint("SimpleDateFormat")
    fun getDate(milliSeconds: Long, dateFormat: String?): String? {
        // Create a DateFormatter object for displaying date in specified format.
        val formatter = SimpleDateFormat(dateFormat)

        // Create a calendar object that will convert the date and time value in milliseconds to date.
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = milliSeconds
        return formatter.format(calendar.time)
    }
}