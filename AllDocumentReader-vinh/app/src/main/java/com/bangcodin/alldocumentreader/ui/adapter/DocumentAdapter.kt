/*
 *  Created by Nguyễn Kim Khánh on 15:26, 08/08/2022
 *     dtako.developer@gmail.com
 *     Last modified 15:26, 08/08/2022
 *     Copyright (c) 2022.
 *     All rights reserved.
 */

package com.bangcodin.alldocumentreader.ui.adapter

import android.content.Context
import android.util.Log
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.viewbinding.ViewBinding
import android.widget.PopupWindow
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bangcodin.alldocumentreader.R
import com.bangcodin.alldocumentreader.databinding.ItemDocumentBinding
import com.bangcodin.alldocumentreader.databinding.ItemDocumentBindingImpl
import com.bangcodin.alldocumentreader.databinding.MenuMoreBinding
import com.bangcodin.alldocumentreader.db.model.DocumentItem
import com.bangcodin.alldocumentreader.ui.`interface`.MenuClickListener
import com.bangcodin.alldocumentreader.ui.base.BaseListAdapter
import com.bangcodin.alldocumentreader.utils.Constants

class DocumentComparator : DiffUtil.ItemCallback<DocumentItem>() {
    override fun areItemsTheSame(oldItem: DocumentItem, newItem: DocumentItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DocumentItem, newItem: DocumentItem): Boolean {
        return oldItem.title == newItem.title
    }

}

class DocumentAdapter(private val applicationContext:Context,val listener: MenuClickListener)
    : BaseListAdapter<DocumentItem>(DocumentComparator()) {
    override fun getLayoutResource(viewType: Int): Int = R.layout.item_document

    override fun onBindData(holder: RecyclerView.ViewHolder?, T: Any, position: Int) {
        val documentVH = holder as DViewHolder
        val binding:ItemDocumentBinding = documentVH.binding as ItemDocumentBinding
        binding.ivIconMore.setOnClickListener {
            showMenuMore(binding.ivIconMore)
        }
        binding.document = T as DocumentItem

        binding.container.setOnClickListener {
            listener.onClick(binding.document as DocumentItem)
        }

        documentVH.binding.executePendingBindings()
    }
    private fun showMenuMore(view: View){
        val popupInflater = applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupBind = MenuMoreBinding.inflate(popupInflater)
        val popupWindow = PopupWindow(popupBind.root, ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,true)
        popupWindow.showAsDropDown(view,-300,-450,Gravity.CENTER)
        popupBind.containerRename.setOnClickListener {
            Toast.makeText(applicationContext,"Rename is click",Toast.LENGTH_SHORT).show()
            popupWindow.dismiss()
        }
        popupBind.containerPrint.setOnClickListener {

            Toast.makeText(applicationContext,"Print is click",Toast.LENGTH_SHORT).show()
            popupWindow.dismiss()
        }
        popupBind.containerFavorite.setOnClickListener {

            Toast.makeText(applicationContext,"Favorited",Toast.LENGTH_SHORT).show()
            popupWindow.dismiss()
        }
        popupBind.containerShare.setOnClickListener {
            Toast.makeText(applicationContext,"Share is click",Toast.LENGTH_SHORT).show()
            popupWindow.dismiss()
        }
    }
}