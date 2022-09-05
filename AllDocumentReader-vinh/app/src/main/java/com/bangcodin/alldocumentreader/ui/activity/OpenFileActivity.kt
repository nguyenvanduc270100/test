/*
 *  Created by Nguyễn Thành Vinh on 8/24/22, 12:52 PM
 *     ntvtht1204@gmail.com
 *     Last modified 8/24/22, 12:51 PM
 *     Copyright (c) 2022.
 *     All rights reserved.
 */

package com.bangcodin.alldocumentreader.ui.activity

import androidx.viewbinding.ViewBinding
import com.bangcodin.alldocumentreader.databinding.ActivityOpenFileBinding
import com.bangcodin.alldocumentreader.ui.base.BaseActivity
import java.io.FileInputStream

class OpenFileActivity : BaseActivity() {
    private lateinit var binding: ActivityOpenFileBinding
    override fun setLayout(): ViewBinding {
       return ActivityOpenFileBinding.inflate(layoutInflater) as ViewBinding
    }

    override fun initView(binding: ViewBinding) {
        this.binding = binding as ActivityOpenFileBinding
    }
    private fun convertPPT(){

    }
}