/*
 *  Created by Nguyễn Thành Vinh on 8/27/22, 8:40 PM
 *     ntvtht1204@gmail.com
 *     Last modified 8/27/22, 8:39 PM
 *     Copyright (c) 2022.
 *     All rights reserved.
 */

package com.bangcodin.alldocumentreader.ui.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Environment
import android.provider.DocumentsContract
import android.util.Log
import android.widget.SearchView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bangcodin.alldocumentreader.DocumentApplication
import com.bangcodin.alldocumentreader.FileOpen
import com.bangcodin.alldocumentreader.databinding.ActivitySearchBinding
import com.bangcodin.alldocumentreader.db.model.DocumentItem
import com.bangcodin.alldocumentreader.db.viewmodel.DocumentViewModel
import com.bangcodin.alldocumentreader.db.viewmodel.DocumentViewModelFactory
import com.bangcodin.alldocumentreader.ui.`interface`.MenuClickListener
import com.bangcodin.alldocumentreader.ui.adapter.DocumentAdapter
import com.bangcodin.alldocumentreader.ui.adapter.DocumentGridAdapter
import com.bangcodin.alldocumentreader.ui.base.BaseActivity
import com.bangcodin.alldocumentreader.ui.base.BaseListAdapter
import com.bangcodin.alldocumentreader.utils.Common
import com.bangcodin.alldocumentreader.utils.Constants
import java.io.File

class SearchActivity : BaseActivity() {
    private lateinit var binding: ActivitySearchBinding
    private lateinit var adapter: BaseListAdapter<DocumentItem>
    private var type:String? = null
    private var type2:String? =  null
    private val documentViewModel : DocumentViewModel by viewModels {
        DocumentViewModelFactory((this.application as DocumentApplication).repository)
    }
    override fun setLayout(): ViewBinding {
        return ActivitySearchBinding.inflate(layoutInflater)
    }

    override fun initView(binding: ViewBinding) {
        this.binding = binding as ActivitySearchBinding
        type= intent.getStringExtra(Constants.TYPE)
        type2 = intent.getStringExtra(Constants.TYPE2)
        Log.d("BBB",type!!)
        setSearch()
        init()
        setRecyclerView()
        setCloseButton()
    }
    private fun init(){
        documentViewModel.listItemDocument.observe(this){
            adapter.submitList(it)

        }
    }
    private fun setSearch(){
        binding.search.requestFocus()
        binding.search.isFocusableInTouchMode = true
        Common.openKeyBoard(this)
        binding.search.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query!=null){
                    searchDatabase(query,type!!,type2!!)
                }
                return true
            }

            override fun onQueryTextChange(query: String?): Boolean {
                if (query!=null){
                    searchDatabase(query,type!!,type2!!)
                }
                return true
            }
        })
        binding.recyclerViewFile.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    binding.search.clearFocus()
                    setCloseKeyBoard()
                }
            }
        })
    }
    private fun searchDatabase(query: String,type:String,type2: String){
        val searchQuery = "%$query%"
        documentViewModel.searchDatabase(searchQuery,type,type2)
    }
    private fun setCloseKeyBoard(){
        binding.search.setOnFocusChangeListener { _, hasFocus ->
            if(!hasFocus){
                Common.closeKeyBoard(this)
            }
        }
    }
    private fun setCloseButton(){
        binding.ivClose.setOnClickListener {
            finish()
        }
    }
    private fun setRecyclerView(){
            val layoutManager = LinearLayoutManager(this)
            this.binding.recyclerViewFile.layoutManager = layoutManager
            adapter = DocumentAdapter(applicationContext, object: MenuClickListener {
            @RequiresApi(Build.VERSION_CODES.O)
            override fun onClick(document: DocumentItem) {

                val myFile = File(document.uriDoc)
                //FileOpen.openFile(applicationContext, myFile)
                openFile(applicationContext, myFile)
            }

        })
            this.binding.recyclerViewFile.adapter = adapter
            setNormal()
    }
    private fun setNormal(){
        documentViewModel.sortNormal(type!!,type2!!)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }

    fun openFile(activity: Context, selectedItem: File) {
// Get URI and MIME type of file
        val uri = FileProvider.getUriForFile(activity.applicationContext, "com.bangcodin.alldocumentreader", selectedItem)
//    val uri = Uri.fromFile(selectedItem).normalizeScheme()
        val mime: String = uri.toString()

// Open file with user selected app
        val intent = Intent()
        intent.action = Intent.ACTION_VIEW
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION)
//    intent.data = uri
//    intent.type = mime
        intent.setDataAndType(uri, mime)
        return activity.startActivity(intent)
    }

}