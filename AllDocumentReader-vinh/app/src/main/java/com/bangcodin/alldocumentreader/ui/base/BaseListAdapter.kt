/*
 *  Created by Nguyễn Kim Khánh on 10:06, 08/08/2022
 *     dtako.developer@gmail.com
 *     Last modified 10:06, 08/08/2022
 *     Copyright (c) 2022.
 *     All rights reserved.
 */

package com.bangcodin.alldocumentreader.ui.base

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.AsyncDifferConfig
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import java.util.concurrent.Executors


abstract class BaseListAdapter<T : Any>(Comparator: DiffUtil.ItemCallback<T>) :
    ListAdapter<T, BaseListAdapter.DViewHolder>(
        AsyncDifferConfig.Builder(Comparator)
            .setBackgroundThreadExecutor(Executors.newSingleThreadExecutor())
            .build()
    ) {

    class DViewHolder(val binding: ViewDataBinding) : RecyclerView.ViewHolder(binding.root)

    abstract fun getLayoutResource(viewType: Int): Int

    abstract fun onBindData(holder: RecyclerView.ViewHolder?, T: Any, position: Int)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DViewHolder {
        return DViewHolder(
            DataBindingUtil.inflate<ViewDataBinding>(
                LayoutInflater.from(parent.context),
                getLayoutResource(viewType),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: DViewHolder, position: Int) {
        onBindData(holder, getItem(position), position)
    }
}