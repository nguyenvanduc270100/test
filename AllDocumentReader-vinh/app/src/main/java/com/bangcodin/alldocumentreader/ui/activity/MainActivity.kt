/*
 *  Created by Nguyễn Kim Khánh on 17:41, 09/08/2022
 *     dtako.developer@gmail.com
 *     Last modified 22:25, 08/08/2022
 *     Copyright (c) 2022.
 *     All rights reserved.
 */
package com.bangcodin.alldocumentreader.ui.activity

import androidx.activity.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.viewbinding.ViewBinding
import com.bangcodin.alldocumentreader.DocumentApplication
import com.bangcodin.alldocumentreader.databinding.ActivityMainBinding
import com.bangcodin.alldocumentreader.db.viewmodel.DocumentViewModel
import com.bangcodin.alldocumentreader.db.viewmodel.DocumentViewModelFactory
import com.bangcodin.alldocumentreader.ui.base.BaseActivity

class MainActivity : BaseActivity() {
    private lateinit var binding: ActivityMainBinding

    private val documentViewModel: DocumentViewModel by viewModels {
        DocumentViewModelFactory((this.application as DocumentApplication).repository)
    }

    override fun setLayout(): ViewBinding =
        ActivityMainBinding.inflate(layoutInflater) as ViewBinding

    override fun initView(binding: ViewBinding) {

    }

}