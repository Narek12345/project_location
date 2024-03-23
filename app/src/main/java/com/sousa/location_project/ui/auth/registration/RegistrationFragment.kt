package com.sousa.location_project.ui.auth.registration

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.ImageDecoder
import android.os.Build
import android.provider.MediaStore
import android.view.LayoutInflater
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.DialogFragment
import com.sousa.location_project.R
import com.sousa.location_project.ui.base.BaseFragment
import com.sousa.location_project.databinding.FragmentRegistrationBinding
import com.sousa.location_project.ui.MainActivity
import com.sousa.location_project.utils.getImageFromInternalStorage
import com.sousa.location_project.utils.saveToInternalStorage
import com.sousa.location_project.ui.auth.AuthViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel

class RegistrationFragment : BaseFragment<FragmentRegistrationBinding>() {

    val viewModel: AuthViewModel by viewModel()
    override fun bind(inflater: LayoutInflater): FragmentRegistrationBinding {
        return FragmentRegistrationBinding.inflate(inflater)
    }

    override fun initView() {

    }

    override fun setListeners() {
        viewModel.photoUrl.observe(viewLifecycleOwner){
            binding?.choosePhoto?.backgroundTintList = ColorStateList.valueOf(if(it.isNullOrBlank()) requireActivity().getColor(
                R.color.gray) else requireActivity().getColor(R.color.green))
        }
        viewModel.goNext.observe(viewLifecycleOwner){
            if(it){
                startActivity(Intent(requireActivity(),MainActivity::class.java))
                requireActivity().finish()
            }
        }
        binding?.choosePhoto?.setOnClickListener {
            pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }
        binding?.registerBtn?.setOnClickListener {
            val dialog: DialogFragment = AcceptDialog(
                okListener = {
                    register()
                },
                termsOfUse = {
                    val dialog: DialogFragment = TermsOfUseDialog(viewModel.termsOfUse){
                            register()
                        }
                    dialog.show(parentFragmentManager, "TermsOfUseDialog")
                })
            dialog.show(parentFragmentManager, "AcceptDialog")
        }
    }

    fun register(){
        val fio = binding?.nameEdit?.text.toString()
        val age = binding?.ageEdit?.text.toString()
        val city = binding?.cityEdit?.text.toString()
        val address = binding?.addressEdit?.text.toString()
        val code = binding?.codeEdit?.text.toString()

        viewModel.registration(fio,age,city,address,code)
    }

    val pickMedia = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            val bitmap = if (Build.VERSION.SDK_INT < 28) {
                MediaStore.Images.Media.getBitmap(requireActivity().contentResolver, uri!!)
            } else {
                val source =
                    ImageDecoder.createSource(requireActivity().contentResolver, uri!!)
                ImageDecoder.decodeBitmap(source)
            }
            val uriNew = getImageFromInternalStorage(requireActivity(),
                saveToInternalStorage(requireActivity(),bitmap)
            )
            viewModel.photoUrl.value = uriNew.toString()
        }
    }
}