/*
 *  Created by Nguyễn Thành Vinh on 8/12/22, 1:27 PM
 *     ntvtht1204@gmail.com
 *     Last modified 8/12/22, 10:21 AM
 *     Copyright (c) 2022.
 *     All rights reserved.
 */

package com.bangcodin.alldocumentreader.ui.fragment

import android.content.Intent
import androidx.viewbinding.ViewBinding
import com.bangcodin.alldocumentreader.R
import com.bangcodin.alldocumentreader.databinding.FragmentSettingBinding
import com.bangcodin.alldocumentreader.ui.activity.MainActivity
import com.bangcodin.alldocumentreader.ui.base.BaseFragment


class SettingFragment : BaseFragment() {
    private lateinit var binding: FragmentSettingBinding
    override fun initView(viewBinding: ViewBinding) {
        binding = viewBinding as FragmentSettingBinding
        binding.containerLanguage.setOnClickListener {
            startActivity(Intent(requireActivity(),MainActivity::class.java))
        }
    }

    override fun getLayout(): Int {
        return R.layout.fragment_setting
    }

}