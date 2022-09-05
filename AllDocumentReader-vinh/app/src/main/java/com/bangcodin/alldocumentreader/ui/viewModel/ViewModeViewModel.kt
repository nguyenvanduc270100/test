/*
 *  Created by Nguyễn Thành Vinh on 8/18/22, 1:51 PM
 *     ntvtht1204@gmail.com
 *     Last modified 8/18/22, 1:51 PM
 *     Copyright (c) 2022.
 *     All rights reserved.
 */

package com.bangcodin.alldocumentreader.ui.viewModel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.bangcodin.alldocumentreader.R
import com.bangcodin.alldocumentreader.db.viewmodel.DocumentViewModel
import com.bangcodin.alldocumentreader.utils.Constants
import com.bangcodin.alldocumentreader.utils.SharePreference
import kotlinx.coroutines.currentCoroutineContext

class ViewModeViewModel(private val context: Context):ViewModel() {
    var viewMode = MutableLiveData<Int>()
    init {
        viewMode.value = when(SharePreference.getStringPref(context,SharePreference.VIEW_MODE)){
            Constants.LIST_VIEW -> {
                R.drawable.ic_grid
            }
            Constants.GRID_VIEW -> {
                R.drawable.ic_row
            }
            else -> {
                R.drawable.ic_grid
            }
        }
    }
    fun updateViewMode(viewType:Int){
        viewMode.value = viewType
    }
}
class ViewModeViewModelFactory(private val context: Context): ViewModelProvider.Factory{
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ViewModeViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ViewModeViewModel(context) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}