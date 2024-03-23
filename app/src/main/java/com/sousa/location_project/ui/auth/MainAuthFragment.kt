package com.sousa.location_project.ui.auth

import android.view.LayoutInflater
import com.sousa.location_project.R
import com.sousa.location_project.ui.base.BaseFragment
import com.sousa.location_project.databinding.FragmentMainAuthBinding

class MainAuthFragment : BaseFragment<FragmentMainAuthBinding>() {
    override fun bind(inflater: LayoutInflater): FragmentMainAuthBinding {
        return FragmentMainAuthBinding.inflate(inflater)
    }

    override fun initView() {

    }

    override fun setListeners() {
        binding?.loginBtn?.setOnClickListener {
            findNavController().navigate(R.id.action_mainAuthFragment_to_loginFragment)
        }
        binding?.registerBtn?.setOnClickListener {
            findNavController().navigate(R.id.action_mainAuthFragment_to_registrationFragment)
        }
    }

}