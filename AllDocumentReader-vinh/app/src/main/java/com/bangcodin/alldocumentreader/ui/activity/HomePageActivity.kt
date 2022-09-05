/*
 *  Created by Nguyễn Thành Vinh on 8/12/22, 1:39 PM
 *     ntvtht1204@gmail.com
 *     Last modified 8/12/22, 1:39 PM
 *     Copyright (c) 2022.
 *     All rights reserved.
 */

package com.bangcodin.alldocumentreader.ui.activity

import android.content.Intent
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.bangcodin.alldocumentreader.R
import com.bangcodin.alldocumentreader.databinding.ActivityHomePageBinding
import com.bangcodin.alldocumentreader.ui.base.BaseActivity
import com.bangcodin.alldocumentreader.ui.fragment.FavoriteFragment
import com.bangcodin.alldocumentreader.ui.fragment.MyFileFragment
import com.bangcodin.alldocumentreader.ui.fragment.RecentFragment
import com.bangcodin.alldocumentreader.ui.fragment.SettingFragment

class HomePageActivity : BaseActivity() {
    private lateinit var binding: ActivityHomePageBinding
    private val myFileFragment = MyFileFragment()
    private val recentFragment = RecentFragment()
    private val favoriteFragment = FavoriteFragment()
    private val settingFragment = SettingFragment()
    override fun setLayout(): ViewBinding {
        return ActivityHomePageBinding.inflate(layoutInflater) as ViewBinding
    }

    override fun initView(binding: ViewBinding) {
        this.binding = binding as ActivityHomePageBinding
        this.binding.ivLanguage.setOnClickListener {
            startActivity(Intent(this,MainActivity::class.java))
        }
        setNavigation()
    }
    private fun setNavigation(){
        setFragment()
        this.binding.bottomNavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_my_file -> {
                    setCurrentFragment(myFileFragment)
                    this.binding.tvHomePage.text = resources.getString(R.string.all_document)
                }
                R.id.menu_recent -> {
                    setCurrentFragment(recentFragment)
                    this.binding.tvHomePage.text = resources.getString(R.string.recent)
                }
                R.id.menu_favorite -> {
                    setCurrentFragment(favoriteFragment)
                    this.binding.tvHomePage.text = resources.getString(R.string.favorite)
                }
                R.id.menu_settings -> {
                    setCurrentFragment(settingFragment)
                    this.binding.tvHomePage.text = resources.getString(R.string.settings)
                }
            }
            true
        }
    }
    private fun setCurrentFragment(fragment: Fragment){
        supportFragmentManager.beginTransaction().apply {
            replace(R.id.fragment_container,fragment)
            commit()
        }
    }
    private fun setFragment(){
        when(binding.bottomNavigation.selectedItemId){
            R.id.menu_my_file -> {
                setCurrentFragment(myFileFragment)
                this.binding.tvHomePage.text = resources.getString(R.string.all_document)
            }
            R.id.menu_recent -> {
                setCurrentFragment(recentFragment)
                this.binding.tvHomePage.text = resources.getString(R.string.recent)
            }
            R.id.menu_favorite -> {
                setCurrentFragment(favoriteFragment)
                this.binding.tvHomePage.text = resources.getString(R.string.favorite)
            }
            R.id.menu_settings -> {
                setCurrentFragment(settingFragment)
                this.binding.tvHomePage.text = resources.getString(R.string.settings)
            }
            else ->{
                setCurrentFragment(myFileFragment)
                this.binding.tvHomePage.text = resources.getString(R.string.all_document)
            }
        }
    }
    override fun onResume() {
        super.onResume()
        setFragment()
    }
}