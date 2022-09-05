/*
 *  Created by Nguyễn Thành Vinh on 8/12/22, 1:27 PM
 *     ntvtht1204@gmail.com
 *     Last modified 8/12/22, 10:21 AM
 *     Copyright (c) 2022.
 *     All rights reserved.
 */

package com.bangcodin.alldocumentreader.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.bangcodin.alldocumentreader.DocumentApplication
import com.bangcodin.alldocumentreader.R
import com.bangcodin.alldocumentreader.databinding.FragmentFavoriteBinding
import com.bangcodin.alldocumentreader.databinding.FragmentRecentBinding
import com.bangcodin.alldocumentreader.db.viewmodel.DocumentViewModel
import com.bangcodin.alldocumentreader.db.viewmodel.DocumentViewModelFactory
import com.bangcodin.alldocumentreader.ui.base.BaseFragment

class RecentFragment : BaseFragment() {
    private lateinit var binding: FragmentRecentBinding
    private val documentViewModel: DocumentViewModel by viewModels {
        DocumentViewModelFactory((requireActivity().application as DocumentApplication).repository)
    }
    override fun initView(viewBinding: ViewBinding) {
        this.binding= viewBinding as FragmentRecentBinding

    }

    override fun getLayout(): Int {
        return R.layout.fragment_recent
    }


}