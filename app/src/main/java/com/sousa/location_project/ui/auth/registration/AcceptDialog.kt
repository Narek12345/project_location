package com.sousa.location_project.ui.auth.registration

import android.app.AlertDialog
import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.Uri
import android.os.Bundle
import android.text.SpannableString
import android.text.Spanned
import android.text.TextPaint
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.fragment.app.DialogFragment
import com.sousa.location_project.R
import com.sousa.location_project.databinding.AcceptDialogBinding

class AcceptDialog(val okListener: () -> Unit,val termsOfUse: () -> Unit) : DialogFragment() {

    var binding: AcceptDialogBinding? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = AcceptDialogBinding.inflate(
            layoutInflater
        )
        val builder = AlertDialog.Builder(requireActivity())
        isCancelable = true
        builder.setView(binding!!.root)
        val dialog: Dialog = builder.create()

        val clickableSpan: ClickableSpan = object : ClickableSpan() {
            override fun onClick(textView: View) {
                termsOfUse()
                dismiss()
            }

            override fun updateDrawState(ds: TextPaint) {
                super.updateDrawState(ds)
                ds.color = resources.getColor(R.color.blue, null)
            }
        }
        val str = getString(R.string.accept_dialog_text)
        val termsOfUse = getString(R.string.terms_of_use_accept)
        val ss = SpannableString("$str $termsOfUse")
        val i1 = ss.toString().indexOf(termsOfUse)
        ss.setSpan(clickableSpan, i1, str.length, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding!!.textView.text = ss
        binding!!.textView.movementMethod = LinkMovementMethod.getInstance()
        binding!!.textView.highlightColor = resources.getColor(R.color.blue, null)

        binding!!.btnSave.setOnClickListener { view: View? ->
            okListener()
            dismiss()
        }
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        return dialog
    }

}