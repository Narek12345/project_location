package com.sousa.location_project.ui.auth.registration

import android.app.AlertDialog
import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.method.ScrollingMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.fragment.app.DialogFragment
import com.sousa.location_project.R
import com.sousa.location_project.databinding.AcceptDialogBinding
import com.sousa.location_project.databinding.TermsOfUseDialogBinding

class TermsOfUseDialog(val text:String,val okListener: () -> Unit) : DialogFragment() {

    var binding: TermsOfUseDialogBinding? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = TermsOfUseDialogBinding.inflate(
            layoutInflater
        )
        val builder = AlertDialog.Builder(requireActivity())
        isCancelable = true
        builder.setView(binding!!.root)
        val dialog: Dialog = builder.create()

        binding!!.textView.text = text
        binding!!.textView.movementMethod = ScrollingMovementMethod.getInstance()

        binding!!.btnSave.setOnClickListener { view: View? ->
            okListener()
            dismiss()
        }
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

}