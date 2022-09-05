/*
 *  Created by Nguyễn Thành Vinh on 8/18/22, 3:40 PM
 *     ntvtht1204@gmail.com
 *     Last modified 8/18/22, 3:40 PM
 *     Copyright (c) 2022.
 *     All rights reserved.
 */

package com.bangcodin.alldocumentreader.ui.adapter

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bangcodin.alldocumentreader.R
import com.bangcodin.alldocumentreader.databinding.ItemDocumentGridBinding
import com.bangcodin.alldocumentreader.db.model.DocumentItem
import com.bangcodin.alldocumentreader.ui.base.BaseListAdapter

class DocumentGridComparator : DiffUtil.ItemCallback<DocumentItem>() {
    override fun areItemsTheSame(oldItem: DocumentItem, newItem: DocumentItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: DocumentItem, newItem: DocumentItem): Boolean {
        return oldItem.title == newItem.title
    }

}

class DocumentGridAdapter: BaseListAdapter<DocumentItem>(DocumentGridComparator()) {

    override fun getLayoutResource(viewType: Int): Int = R.layout.item_document_grid

    override fun onBindData(holder: RecyclerView.ViewHolder?, T: Any, position: Int) {
        val documentVH = holder as DViewHolder
        val binding = documentVH.binding as ItemDocumentGridBinding
        binding.document = T as DocumentItem
        documentVH.binding.executePendingBindings()
    }
}