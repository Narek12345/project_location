package com.sousa.location_project.ui.main.settings

import android.view.LayoutInflater
import com.sousa.location_project.databinding.FragmentSettingsBinding
import com.sousa.location_project.ui.MainActivity
import com.sousa.location_project.ui.base.BaseFragment
import com.sousa.location_project.utils.Prefs

class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {
    override fun bind(inflater: LayoutInflater): FragmentSettingsBinding {
        return FragmentSettingsBinding.inflate(inflater)
    }

    override fun initView() {
        binding!!.switchDarkTheme.setChecked(Prefs().isDarkTheme)
    }

    override fun setListeners() {
        binding?.backBtn?.setOnClickListener {
            findNavController().popBackStack()
        }
        binding?.switchDarkTheme?.setOnCheckedChangeListener { compoundButton, b ->
            (requireActivity() as MainActivity).setDarkMode(b)
        }
    }

}