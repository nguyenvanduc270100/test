package com.bangcodin.alldocumentreader.ui.base

import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.provider.Settings
import android.view.View
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.viewbinding.ViewBinding
import com.bangcodin.alldocumentreader.utils.PERMISSION_ALL
import com.bangcodin.alldocumentreader.utils.PermissionHelper
import com.bangcodin.alldocumentreader.utils.setAppLocale
import com.google.android.material.snackbar.Snackbar
import com.shashank.sony.fancytoastlib.FancyToast


abstract class BaseActivity : AppCompatActivity() {

    private lateinit var binding: ViewBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setAppLocale(this, "vi")
        binding = setLayout()
        setContentView(binding.root)

    }




    protected abstract fun setLayout(): ViewBinding
    protected abstract fun initView(binding: ViewBinding)

    open fun openActivity(destinationClass: Class<*>, canBack: Boolean?) {
        val intent = Intent(this@BaseActivity, destinationClass)
        if (canBack != null && canBack == false) {
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        }
        startActivity(intent)
        if (canBack==false){
            finish()
        }

    }

    override fun onResume() {
        super.onResume()
        initView(binding)
    }
}