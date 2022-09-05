/*
 *  Created by Nguyễn Thành Vinh on 15:56, 31/08/2022
 *     ntvtht1204@gmail.com
 *     Last modified 15:56, 31/08/2022
 *     Copyright (c) 2022.
 *     All rights reserved.
 */

package com.bangcodin.alldocumentreader.ui.activity

import android.net.Uri
import android.provider.MediaStore
import android.util.Log
import androidx.core.net.toUri
import androidx.viewbinding.ViewBinding
import com.bangcodin.alldocumentreader.databinding.ActivityPdfBinding
import com.bangcodin.alldocumentreader.ui.base.BaseActivity
import java.io.File


class PdfActivity: BaseActivity() {
    private lateinit var binding: ActivityPdfBinding

    override fun setLayout(): ViewBinding {
        return ActivityPdfBinding.inflate(layoutInflater) as ViewBinding
    }

    override fun initView(binding: ViewBinding) {
        this.binding = binding as ActivityPdfBinding
        val uri = intent.getStringExtra("path")
        Log.d("NV",uri.toString())
//        val file = File(uri!!)
//        val path = Uri.parse(file.toString())
        Log.d("NV",uri.toString())
        val uri1:Uri =  File(uri).toUri()
        this.binding.pdfView.fromUri(uri1).load()
    }


}