package com.yveschiong.personalrecordbook.ui.signature

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.AsyncTask
import android.os.Bundle
import com.github.gcacace.signaturepad.views.SignaturePad
import com.yveschiong.personalrecordbook.R
import com.yveschiong.personalrecordbook.common.base.BaseFragment
import com.yveschiong.personalrecordbook.common.extensions.showSavingSignatureProgressDialog
import com.yveschiong.personalrecordbook.databinding.FragmentSignatureBinding
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_signature.*
import javax.inject.Inject

class SignatureFragment : BaseFragment<FragmentSignatureBinding>() {

    @Inject
    lateinit var viewModelFactory: SignatureViewModelFactory

    lateinit var viewModel: SignatureViewModel

    companion object {

        fun newInstance(): SignatureFragment {
            return SignatureFragment()
        }
    }

    override val layoutId: Int
        get() = R.layout.fragment_signature

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(SignatureViewModel::class.java)
        binding.vm = viewModel

        viewModel.clickedSaveSignature.simpleSubscribe { saveSignature(signature_pad) }
    }

    private fun saveSignature(signaturePad: SignaturePad) {
        if (signaturePad.isEmpty) {
            return
        }

        val bitmap = signaturePad.transparentSignatureBitmap

        val dialog = showSavingSignatureProgressDialog()

        // Only for testing right now
        AsyncTask.execute {
            Thread.sleep(2000)
            activity?.runOnUiThread {
                dialog.cancel()
            }
        }
    }
}
