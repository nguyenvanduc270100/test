/*
 *  Created by Nguyễn Thành Vinh on 8/12/22, 1:27 PM
 *     ntvtht1204@gmail.com
 *     Last modified 8/12/22, 10:21 AM
 *     Copyright (c) 2022.
 *     All rights reserved.
 */

package com.bangcodin.alldocumentreader.ui.fragment

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.icu.text.CaseMap
import android.view.View
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import com.bangcodin.alldocumentreader.DocumentApplication
import com.bangcodin.alldocumentreader.R
import com.bangcodin.alldocumentreader.databinding.FragmentMyFileBinding
import com.bangcodin.alldocumentreader.db.viewmodel.DocumentViewModel
import com.bangcodin.alldocumentreader.db.viewmodel.DocumentViewModelFactory
import com.bangcodin.alldocumentreader.ui.activity.AllFileActivity
import com.bangcodin.alldocumentreader.ui.activity.HomePageActivity
import com.bangcodin.alldocumentreader.ui.activity.OpenFileActivity
import com.bangcodin.alldocumentreader.ui.activity.SearchActivity
import com.bangcodin.alldocumentreader.ui.base.BaseFragment
import com.bangcodin.alldocumentreader.utils.Common
import com.bangcodin.alldocumentreader.utils.Constants
import com.google.android.material.bottomnavigation.BottomNavigationView


class MyFileFragment : BaseFragment() {
    private lateinit var binding:FragmentMyFileBinding
    private lateinit var homePageActivity: HomePageActivity
    private val documentViewModel: DocumentViewModel by viewModels {
        DocumentViewModelFactory((requireActivity().application as DocumentApplication).repository)
    }
    override fun initView(viewBinding: ViewBinding) {
        binding = viewBinding as FragmentMyFileBinding
        binding.lifecycleOwner = viewLifecycleOwner
        homePageActivity = activity as HomePageActivity

        setOnClickItem()
        setTitleText(context)
        setSearchView()
    }

    override fun getLayout(): Int {
        return R.layout.fragment_my_file
    }
    private fun setSearchView(){
        binding.searchView.setOnClickListener {
            val intent = Intent(requireActivity(),SearchActivity::class.java)
            intent.putExtra(Constants.TYPE,Constants.ALL_FILE)
            intent.putExtra(Constants.TYPE2," ")
            startActivity(intent)
        }
    }
    private fun setOnClickItem(){

        binding.containerAllFile.setOnClickListener {
            openActivitys(Constants.ALL_FILE," ",binding.tvAllFile.text.toString())
        }
        binding.containerPdf.setOnClickListener {
            openActivitys(Constants.PDF," ",binding.tvPdfFile.text.toString())
        }
        binding.containerWordFile.setOnClickListener {
            openActivitys(Constants.DOC,Constants.DOCX,binding.tvWordFile.text.toString())
        }
        binding.containerPowerPoint.setOnClickListener {
            openActivitys(Constants.PPT,Constants.PPTX,binding.tvPowerPoint.text.toString())
        }
        binding.containerExcelFile.setOnClickListener {
            openActivitys(Constants.XLS,Constants.XLSX,binding.tvExcelFile.text.toString())
        }
        binding.containerTextFile.setOnClickListener {
            openActivitys(Constants.TXT," ",binding.tvTextFile.text.toString())
        }
    }
    private fun openActivitys(type:String,type2:String,title: String){
        val intent = Intent(activity,AllFileActivity::class.java)
        intent.putExtra(Constants.TYPE,type)
        intent.putExtra(Constants.TYPE2,type2)
        intent.putExtra(Constants.TITLE,title)
        startActivity(intent)
    }
    @SuppressLint("SetTextI18n")
    private fun setTitleText(context: Context?){
        documentViewModel.allDocuments.observe(viewLifecycleOwner){
            binding.tvAllFile.text = context?.resources?.getString(R.string.all_file) + " (" + it.size.toString() + ")"
        }
        documentViewModel.allFilePDF.observe(viewLifecycleOwner){
            binding.tvPdfFile.text = context?.resources?.getString(R.string.pdf_file) + " (" + it.size.toString() + ")"
        }
        documentViewModel.allFileWord.observe(viewLifecycleOwner){
            binding.tvWordFile.text = context?.resources?.getString(R.string.word_file) + " (" + it.size.toString() + ")"
        }
        documentViewModel.allFileExcel.observe(viewLifecycleOwner){
            binding.tvExcelFile.text =context?.resources?.getString(R.string.excel_file) + " (" + it.size.toString() + ")"
        }
        documentViewModel.allFilePPT.observe(viewLifecycleOwner){
            binding.tvPowerPoint.text = context?.resources?.getString(R.string.power_point) + " (" + it.size.toString() + ")"
        }
        documentViewModel.allFileText.observe(viewLifecycleOwner){
            binding.tvTextFile.text = context?.resources?.getString(R.string.text_file) + " (" + it.size.toString() + ")"
        }
    }

}