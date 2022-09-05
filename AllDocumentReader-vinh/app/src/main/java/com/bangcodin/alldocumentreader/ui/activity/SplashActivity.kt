/*
 *  Created by Nguyễn Thành Vinh on 18:06, 11/08/2022
 *     ntvtht1204@gmail.com
 *     Last modified 18:06, 11/08/2022
 *     Copyright (c) 2022.
 *     All rights reserved.
 */

package com.bangcodin.alldocumentreader.ui.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Environment
import android.provider.Settings
import androidx.activity.viewModels
import androidx.core.app.ActivityCompat
import androidx.viewbinding.ViewBinding
import com.bangcodin.alldocumentreader.DocumentApplication
import com.bangcodin.alldocumentreader.databinding.ActivitySplashBinding
import com.bangcodin.alldocumentreader.db.viewmodel.DocumentViewModel
import com.bangcodin.alldocumentreader.db.viewmodel.DocumentViewModelFactory
import com.bangcodin.alldocumentreader.ui.base.BaseActivity
import com.bangcodin.alldocumentreader.utils.PERMISSION_ALL
import com.bangcodin.alldocumentreader.utils.PermissionHelper
import com.google.android.material.snackbar.Snackbar
import com.shashank.sony.fancytoastlib.FancyToast

@SuppressLint("CustomSplashScreen")
class SplashActivity : BaseActivity() {
    private lateinit var binding: ActivitySplashBinding
    private var isSHOW: Boolean? = true
    private val documentViewModel: DocumentViewModel by viewModels {
        DocumentViewModelFactory((this.application as DocumentApplication).repository)
    }

    override fun setLayout(): ViewBinding {
        return ActivitySplashBinding.inflate(layoutInflater) as ViewBinding
    }

    override fun initView(binding: ViewBinding) {
        this.binding = binding as ActivitySplashBinding
        if (!PermissionHelper.hasPermissions(this, *PermissionHelper.PERMISSIONS)) {
            ActivityCompat.requestPermissions(this, PermissionHelper.PERMISSIONS, PERMISSION_ALL)
        } else {
            if(Build.VERSION.SDK_INT>=Build.VERSION_CODES.R){
                if (Environment.isExternalStorageManager()) {
                    documentViewModel.getAllDocuments(contentResolver)
                    setValueProgress(10, 1000L)
                    documentViewModel.allDocuments.observe(this) {
                        if (it!=null && isSHOW==true) {
                            setValueProgress(5, 500L)
                        }
                    }

                } else { //request for the permission
                    val intent = Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION)
                    val uri = Uri.fromParts("package", packageName, null)
                    intent.data = uri
                    startActivity(intent)
                }
            }else{
                documentViewModel.getAllDocuments(contentResolver)
                setValueProgress(10, 1000L)
                documentViewModel.allDocuments.observe(this) {
                    if (it!=null && isSHOW==true) {
                        setValueProgress(5, 500L)
                    }
                }
            }

        }


    }
    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>, grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            PERMISSION_ALL -> {
                // If request is cancelled, the result arrays are empty.
                if ((grantResults.isNotEmpty() && grantResults.all { it == PackageManager.PERMISSION_GRANTED })) {
                    // Permission is granted. Continue the action or workflow
                    // in your app.
                    documentViewModel.getAllDocuments(contentResolver)
                    setValueProgress(10, 1000L)
                    documentViewModel.allDocuments.observe(this) {
                        if (it!=null && isSHOW==true) {
                            setValueProgress(5, 500L)
                        }
                    }
                } else {
                    // Explain to the user that the feature is unavailable because
                    // the features requires a permission that the user has denied.
                    // At the same time, respect the user's decision. Don't link to
                    // system settings in an effort to convince the user to change
                    // their decision.
                    Snackbar.make(
                        binding.root,
                        "Bạn cần cung cấp quyền truy cập để ứng dụng có thể hoạt động!",
                        Snackbar.LENGTH_LONG
                    )
                        .setAction("Đồng ý") {
                            FancyToast.makeText(
                                this,
                                "Vui lòng đóng ứng dụng và cấp lại quyền truy cập.",
                                FancyToast.LENGTH_SHORT,
                                FancyToast.CONFUSING,
                                false
                            ).show()
                            this@SplashActivity.finish()
                        }.show()
                }
                return
            }
            // Add other 'when' lines to check for other
            // permissions this app might request.
            else -> {
                // Ignore all other requests.
            }
        }
    }
    private fun setValueProgress(value: Int, delay: Long) {
        binding.root.postDelayed({
            binding.progressHorizontal.progress += value

            if (binding.progressHorizontal.progress <= 30 && delay == 1000L) {
                setValueProgress(value, delay)
            }
            if (binding.progressHorizontal.progress == 90 && delay == 500L) {
                isSHOW = false
                openActivity(HomePageActivity::class.java, false)
            }

            if (delay == 500L) {
                setValueProgress(value, delay)
            }

        }, delay)
    }
}