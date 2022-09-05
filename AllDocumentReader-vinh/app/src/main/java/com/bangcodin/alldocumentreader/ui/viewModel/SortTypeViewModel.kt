/*
 *  Created by Nguyễn Thành Vinh on 8/19/22, 4:13 PM
 *     ntvtht1204@gmail.com
 *     Last modified 8/19/22, 4:13 PM
 *     Copyright (c) 2022.
 *     All rights reserved.
 */

package com.bangcodin.alldocumentreader.ui.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bangcodin.alldocumentreader.R
import com.bangcodin.alldocumentreader.utils.Constants
import com.bangcodin.alldocumentreader.utils.SharePreference

class SortTypeViewModel(context: Context):ViewModel() {
    var sortType = MutableLiveData<String>()
    init {
        sortType.value = SharePreference.getStringPref(context,SharePreference.SORT_TYPE)
    }
    fun updateViewMode(sortTypes:String){
        sortType.value = sortTypes
    }
}
class SortTypeViewModelFactory(private val context: Context): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SortTypeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return SortTypeViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}