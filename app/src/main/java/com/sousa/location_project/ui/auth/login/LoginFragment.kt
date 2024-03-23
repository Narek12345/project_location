package com.sousa.location_project.ui.auth.login

import android.content.Intent
import android.view.LayoutInflater
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import com.sousa.location_project.ui.base.BaseFragment
import com.sousa.location_project.databinding.FragmentLoginFramentBinding
import com.sousa.location_project.ui.MainActivity
import com.sousa.location_project.ui.auth.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel


class LoginFragment : BaseFragment<FragmentLoginFramentBinding>() {

    val viewModel: AuthViewModel by viewModel()
    override fun bind(inflater: LayoutInflater): FragmentLoginFramentBinding {
        return FragmentLoginFramentBinding.inflate(inflater)
    }

    override fun initView() {

    }

    override fun setListeners() {
        viewModel.goNext.observe(viewLifecycleOwner){
            if(it){
                startActivity(Intent(requireActivity(), MainActivity::class.java))
                requireActivity().finish()
            }
        }
        binding?.loginBtn?.setOnClickListener {
            val code = binding?.codeEdit?.text.toString()

            viewModel.login(code)
        }
    }

}