package com.mobile.cleancase.presentation.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.mobile.cleancase.presentation.navigation.UiNavigation

typealias Inflate<T> = (LayoutInflater, ViewGroup?, Boolean) -> T

abstract class BaseFragment<VB : ViewBinding>(private val inflate: Inflate<VB>) : Fragment() {

    private var viewBinding: VB? = null
    protected val binding get() = viewBinding!!
    open lateinit var actionBar: ActionBar
    open val uiNavigation = UiNavigation.BACK

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewBinding = inflate.invoke(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        viewBinding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        actionBar = (activity as? AppCompatActivity)?.supportActionBar!!
        initView()
        fetchData()
        observeData()
        initNavigation(uiNavigation)
    }

    private fun initNavigation(uiNavigation: UiNavigation) {
        when (uiNavigation) {
            UiNavigation.BACK -> {
                actionBar.setDisplayHomeAsUpEnabled(true)
                actionBar.setHomeButtonEnabled(true)
            }
            UiNavigation.ROOT -> {
                actionBar.setDisplayHomeAsUpEnabled(false)
                actionBar.setHomeButtonEnabled(false)
            }
        }
    }

    open fun fetchData() {
        // can be overridden
    }

    open fun observeData() {
        // can be overridden
    }

    open fun initView() {
        // can be overridden
    }
}