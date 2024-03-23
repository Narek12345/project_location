package com.sousa.location_project.ui.main.profile

import android.view.LayoutInflater
import com.sousa.location_project.R
import com.sousa.location_project.ui.base.BaseFragment
import com.sousa.location_project.databinding.FragmentProfileBinding
import com.sousa.location_project.ui.auth.AuthViewModel
import com.squareup.picasso.Picasso
import org.koin.androidx.viewmodel.ext.android.viewModel

class ProfileFragment : BaseFragment<FragmentProfileBinding>() {

    val viewModel: ProfileViewModel by viewModel()

    override fun bind(inflater: LayoutInflater): FragmentProfileBinding {
        return FragmentProfileBinding.inflate(inflater)
    }

    override fun initView() {

    }

    override fun setListeners() {
        viewModel.profile.observe(viewLifecycleOwner){
            Picasso.get().load(it.urlPhoto).placeholder(R.drawable.default_avatar)
                .into(binding?.avatarImage)
            binding?.fioText?.text = it.fio
            binding?.ageText?.text = "Возраст: ${it.age}"
            binding?.cityText?.text = "Город: ${it.city}"
            binding?.addressText?.text = "Адресс: ${it.address}"
            binding?.idText?.text = "ID(Для тех. поддержки): ${it.userId}"
        }
        binding?.backBtn?.setOnClickListener {
            findNavController().popBackStack()
        }
    }

}