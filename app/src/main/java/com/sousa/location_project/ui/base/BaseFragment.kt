package com.sousa.location_project.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.core.content.ContextCompat
import androidx.core.view.ViewCompat
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import androidx.core.view.updateLayoutParams
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.viewbinding.ViewBinding
import com.sousa.location_project.R

abstract class BaseFragment<bindingType : ViewBinding?>: Fragment() {
    var binding:bindingType?=null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = bind(inflater)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        setLightThemeEnable(isLightTheme)
        setListeners()
    }

    fun setLightThemeEnable(isEnable:Boolean){
        changeScreenSystemUiController(true)
        WindowInsetsControllerCompat(requireActivity().window, requireActivity().window.decorView).apply {
            isAppearanceLightStatusBars = isEnable
            isAppearanceLightNavigationBars = isEnable
        }
    }

    private fun changeScreenSystemUiController(isFullScreen: Boolean) {
        requireActivity().window?.also {
            it.statusBarColor = ContextCompat.getColor(requireContext(), R.color.gray)
            it.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            WindowCompat.setDecorFitsSystemWindows(it, false)
            ViewCompat.setOnApplyWindowInsetsListener(binding!!.root) { root, windowInset ->
                val inset = windowInset.getInsets(WindowInsetsCompat.Type.systemBars())
                root.updateLayoutParams<ViewGroup.MarginLayoutParams> {
                    leftMargin = inset.left
                    bottomMargin = if(isBottomMargin) inset.bottom else 0
                    topMargin = 0
                    rightMargin = inset.right
                }
                WindowInsetsCompat.CONSUMED
            }
        }
    }

    protected open var isLightTheme:Boolean = true
    protected open var isBottomMargin:Boolean = false
    protected abstract fun bind(inflater: LayoutInflater): bindingType
    protected abstract fun initView()
    protected abstract fun setListeners()
    protected fun findNavController(): NavController {
        return Navigation.findNavController(requireView())
    }
}