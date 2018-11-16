package com.yveschiong.personalrecordbook.ui.signature

import android.app.Activity
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.github.gcacace.signaturepad.views.SignaturePad
import com.yveschiong.data.storage.InternalStorageManager
import com.yveschiong.personalrecordbook.R
import com.yveschiong.personalrecordbook.common.Constants
import com.yveschiong.personalrecordbook.common.base.BaseFragment
import com.yveschiong.personalrecordbook.common.extensions.showSavingSignatureProgressDialog
import com.yveschiong.personalrecordbook.common.metadata.ImageMetadata
import com.yveschiong.personalrecordbook.databinding.FragmentSignatureBinding
import com.yveschiong.personalrecordbook.entities.Person
import dagger.android.support.AndroidSupportInjection
import io.reactivex.Single
import kotlinx.android.synthetic.main.fragment_signature.*
import javax.inject.Inject

class SignatureFragment : BaseFragment<FragmentSignatureBinding>() {

    @Inject
    lateinit var internalStorageManager: InternalStorageManager

    @Inject
    lateinit var viewModelFactory: SignatureViewModelFactory

    lateinit var viewModel: SignatureViewModel

    private lateinit var signatureFilename: String

    var person: Person? = null

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
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(SignatureViewModel::class.java)
        binding.vm = viewModel

        person = arguments?.getParcelable(Constants.EXTRA_PERSON)
        signatureFilename = arguments?.getString(Constants.EXTRA_SIGNATURE_FILE_NAME) ?:
            internalStorageManager.getUniqueFilename()

        viewModel.signedSignature.simpleSubscribe { binding.signed = true }
        viewModel.clickedClearSignature.simpleSubscribe { clearSignature(signature_pad) }
        viewModel.clickedSaveSignature.simpleSubscribe { saveSignature(signature_pad) }

        person?.let {
            val path = internalStorageManager.getImageAbsoluteFilePath(
                InternalStorageManager.CACHE,
                it.id,
                signatureFilename
            )
            binding.signaturePath = path
            binding.metadata = ImageMetadata(internalStorageManager.getLastModifiedTimestamp(path))
        }
    }

    private fun clearSignature(signaturePad: SignaturePad) {
        signaturePad.clear()
        binding.signed = false
    }

    private fun saveSignature(signaturePad: SignaturePad) {
        if (signaturePad.isEmpty) {
            return
        }

        val bitmap = signaturePad.transparentSignatureBitmap

        person?.let {
            val dialog = showSavingSignatureProgressDialog()

            Single.fromCallable {
                internalStorageManager.saveSignature(
                    InternalStorageManager.CACHE,
                    it.id,
                    bitmap,
                    signatureFilename
                )
            }.simpleSubscribe { path ->
                dialog.cancel()

                setResultIntent(path)
                activity?.finish()
            }
        }
    }

    private fun setResultIntent(path: String) {
        val result = Intent()
        result.putExtra(Constants.EXTRA_SIGNATURE_FILE_PATH, path)
        activity?.setResult(Activity.RESULT_OK, result)
    }
}
