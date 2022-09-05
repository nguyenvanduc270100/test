package com.bangcodin.alldocumentreader.ui.activity

import android.content.Context
import android.content.Intent
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.PopupWindow
import androidx.activity.viewModels
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.bangcodin.alldocumentreader.DocumentApplication
import com.bangcodin.alldocumentreader.FileOpen
import com.bangcodin.alldocumentreader.R
import com.bangcodin.alldocumentreader.databinding.ActivityAllFileBinding
import com.bangcodin.alldocumentreader.databinding.MenuSortFileBinding
import com.bangcodin.alldocumentreader.db.model.DocumentItem
import com.bangcodin.alldocumentreader.db.viewmodel.DocumentViewModel
import com.bangcodin.alldocumentreader.db.viewmodel.DocumentViewModelFactory
import com.bangcodin.alldocumentreader.ui.`interface`.MenuClickListener
import com.bangcodin.alldocumentreader.ui.adapter.DocumentAdapter
import com.bangcodin.alldocumentreader.ui.adapter.DocumentGridAdapter
import com.bangcodin.alldocumentreader.ui.base.BaseActivity
import com.bangcodin.alldocumentreader.ui.base.BaseListAdapter
import com.bangcodin.alldocumentreader.ui.viewModel.SortTypeViewModel
import com.bangcodin.alldocumentreader.ui.viewModel.SortTypeViewModelFactory
import com.bangcodin.alldocumentreader.ui.viewModel.ViewModeViewModel
import com.bangcodin.alldocumentreader.ui.viewModel.ViewModeViewModelFactory
import com.bangcodin.alldocumentreader.utils.Constants
import com.bangcodin.alldocumentreader.utils.SharePreference
import java.io.File
import java.net.URL


class AllFileActivity : BaseActivity() {
    private lateinit var binding: ActivityAllFileBinding
    private lateinit var layoutManager: RecyclerView.LayoutManager
    private lateinit var adapter: BaseListAdapter<DocumentItem>
    private var type:String? = null
    private var type2:String? =  null

    private val documentViewModel : DocumentViewModel by viewModels {
        DocumentViewModelFactory((this.application as DocumentApplication).repository)
    }
    private val viewModeViewModel:ViewModeViewModel by viewModels {
        ViewModeViewModelFactory(this)
    }
    private val sortTypeViewModel:SortTypeViewModel by viewModels {
        SortTypeViewModelFactory(this)
    }
    override fun setLayout(): ViewBinding {
        return ActivityAllFileBinding.inflate(layoutInflater)
    }

    override fun initView(binding: ViewBinding) {
        this.binding = binding as ActivityAllFileBinding
        type= intent.getStringExtra(Constants.TYPE)
        type2 = intent.getStringExtra(Constants.TYPE2)
        setViewMode()
        init()
        setRecyclerView()
        setBackButton()
        setSearchButton()
        setMenuSort()
    }
    private fun init(){
        documentViewModel.listItemDocument.observe(this){
            adapter.submitList(it)
            Log.d("BBB",it.size.toString())
        }
    }
    private fun setSearchButton(){
        binding.ivSearchNormal.setOnClickListener {
            val intent = Intent(this,SearchActivity::class.java)
            intent.putExtra(Constants.TYPE,type)
            intent.putExtra(Constants.TYPE2,type2)
            startActivity(intent)
        }
    }
    private fun setBackButton(){
        this.binding.ivBack.setOnClickListener {
            finish()
        }
    }
    private fun setViewMode(){
        binding.ivGrid.setOnClickListener {
            when(getViewMode()){
                Constants.LIST_VIEW -> {
                    SharePreference.setStringPref(this,SharePreference.VIEW_MODE,Constants.GRID_VIEW)
                    viewModeViewModel.updateViewMode(R.drawable.ic_row)
                }
                Constants.GRID_VIEW -> {
                    SharePreference.setStringPref(this,SharePreference.VIEW_MODE,Constants.LIST_VIEW)
                    viewModeViewModel.updateViewMode(R.drawable.ic_grid)
                }
                else -> {
                    SharePreference.setStringPref(this,SharePreference.VIEW_MODE,Constants.GRID_VIEW)
                    viewModeViewModel.updateViewMode(R.drawable.ic_row)
                }
            }
        }
        viewModeViewModel.viewMode.observe(this){
            binding.ivGrid.setImageResource(it)
        }
    }
    private fun getViewMode():String? = SharePreference.getStringPref(this,SharePreference.VIEW_MODE)
    private fun setRecyclerView(){
        this.binding.tvTitleFile.text = intent.getStringExtra(Constants.TITLE)
        viewModeViewModel.viewMode.observe(this){
            when(getViewMode()) {
                Constants.LIST_VIEW -> {
                    layoutManager= LinearLayoutManager(this)
                    adapter = DocumentAdapter(applicationContext, object:MenuClickListener{
                        @RequiresApi(Build.VERSION_CODES.O)
                        override fun onClick(document: DocumentItem) {
                            val myFile = File(document.uriDoc)
                            openFile(applicationContext, myFile)
                        }

                    })
                    Log.d("AAA",getViewMode().toString())
                }
                Constants.GRID_VIEW -> {
                    layoutManager = GridLayoutManager(this, 3)
                    adapter = DocumentGridAdapter()
                    Log.d("AAA",getViewMode().toString())
                }
                else -> {
                    layoutManager= LinearLayoutManager(this)
                    adapter = DocumentAdapter(applicationContext, object:MenuClickListener{
                        override fun onClick(document: DocumentItem) {
                            Log.d("nd", document.title)
                            val myFile = File(document.uriDoc)
                            //FileOpen.openFile(applicationContext, myFile)
                            openFile(applicationContext, myFile)
                        }

                    })
                    Log.d("AAA",getViewMode().toString())
                }
            }
            this.binding.recyclerViewFile.layoutManager = layoutManager
            this.binding.recyclerViewFile.adapter = adapter
            sortTypeViewModel.sortType.observe(this){
                when(it){
                    Constants.DATE_UPDATE -> setSortByDate()
                    Constants.NAME_DESC -> setSortByNameDESC()
                    Constants.NAME_ASC -> setSortByNameASC()
                    else -> setNormal()
                }
            }
        }

    }
    private fun setNormal(){
        documentViewModel.sortNormal(type!!,type2!!)
    }
    private fun setSortByDate(){
        documentViewModel.sortByDateDESC(type!!,type2!!)
    }
    private fun setSortByNameDESC(){
        documentViewModel.sortByNameDESC(type!!,type2!!)
    }
    private fun setSortByNameASC(){
        documentViewModel.sortByNamASC(type!!,type2!!)
    }
    private fun getSortType():String? = SharePreference.getStringPref(this,SharePreference.SORT_TYPE)
    private fun setMenuSort(){
        val popupInflater = applicationContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val popupBind = MenuSortFileBinding.inflate(popupInflater)
        val popupWindow = PopupWindow(popupBind.root, ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT,true)
        binding.ivSort.setOnClickListener {
            popupWindow.showAsDropDown(binding.ivSort,0,-24)
        }
        popupBind.containerSortByDate.setOnClickListener {
            if (getSortType() != Constants.DATE_UPDATE){
                SharePreference.setStringPref(this,SharePreference.SORT_TYPE,Constants.DATE_UPDATE)
                sortTypeViewModel.updateViewMode(Constants.DATE_UPDATE)
            }
        }
        popupBind.containerSortByName.setOnClickListener {
            if (getSortType() != Constants.NAME_ASC){
                SharePreference.setStringPref(this,SharePreference.SORT_TYPE,Constants.NAME_ASC)
                sortTypeViewModel.updateViewMode(Constants.NAME_ASC)
            }
        }
        popupBind.containerSortByName1.setOnClickListener {
            if (getSortType() != Constants.NAME_DESC){
                SharePreference.setStringPref(this,SharePreference.SORT_TYPE,Constants.NAME_DESC)
                sortTypeViewModel.updateViewMode(Constants.NAME_DESC)
            }
        }
        sortTypeViewModel.sortType.observe(this){
            when(it){
                Constants.DATE_UPDATE -> setSortByDate()
                Constants.NAME_DESC -> setSortByNameDESC()
                Constants.NAME_ASC -> setSortByNameASC()
                else -> setNormal()
            }
        }
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