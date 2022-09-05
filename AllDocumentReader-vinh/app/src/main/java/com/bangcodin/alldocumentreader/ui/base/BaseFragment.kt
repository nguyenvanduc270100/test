package com.bangcodin.alldocumentreader.ui.base

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.bangcodin.alldocumentreader.utils.setAppLocale

abstract class BaseFragment : Fragment() {
    private lateinit var binding: ViewBinding
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        setAppLocale(requireContext(), "vi")
        binding =
            DataBindingUtil.inflate(layoutInflater, getLayout(), container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView(binding)
    }

    protected abstract fun initView(viewBinding: ViewBinding)

    abstract fun getLayout(): Int

    open fun openActivity(destinationClass: Class<*>?) {
        startActivity(Intent(activity, destinationClass))
    }
}



