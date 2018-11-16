package com.yveschiong.personalrecordbook.ui.addpersondetail

import android.app.Activity
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import com.yveschiong.data.storage.InternalStorageManager
import com.yveschiong.personalrecordbook.R
import com.yveschiong.personalrecordbook.common.Constants
import com.yveschiong.personalrecordbook.common.base.BaseFragment
import com.yveschiong.personalrecordbook.common.extensions.cropped
import com.yveschiong.personalrecordbook.common.extensions.showAddingRecordProgressDialog
import com.yveschiong.personalrecordbook.common.extensions.toast
import com.yveschiong.personalrecordbook.common.metadata.ImageMetadata
import com.yveschiong.personalrecordbook.databinding.FragmentAddPersonDetailBinding
import com.yveschiong.personalrecordbook.entities.Person
import com.yveschiong.personalrecordbook.ui.signature.SignatureActivity
import dagger.android.support.AndroidSupportInjection
import io.reactivex.Single
import java.util.*
import javax.inject.Inject

class AddPersonDetailFragment : BaseFragment<FragmentAddPersonDetailBinding>() {

    @Inject
    lateinit var internalStorageManager: InternalStorageManager

    @Inject
    lateinit var viewModelFactory: AddPersonDetailViewModelFactory

    lateinit var viewModel: AddPersonDetailViewModel

    lateinit var signatureFilename: String

    var person: Person? = null

    companion object {

        fun newInstance(): AddPersonDetailFragment {
            return AddPersonDetailFragment()
        }
    }

    override val layoutId: Int
        get() = R.layout.fragment_add_person_detail

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(AddPersonDetailViewModel::class.java)
        binding.vm = viewModel

        person = arguments?.getParcelable(Constants.EXTRA_PERSON)
        person?.let {
            viewModel.personId = it.id
        }

        signatureFilename = arguments?.getString(Constants.EXTRA_SIGNATURE_FILE_NAME) ?:
            internalStorageManager.getUniqueFilename()

        viewModel.clickedDate.simpleSubscribe { showDatePicker() }
        viewModel.clickedTime.simpleSubscribe { showTimePicker() }
        viewModel.clickedSignature.simpleSubscribe { showSignatureActivity() }

        handleAddRecord()

        viewModel.signaturePath.observe(this, Observer<String> { path ->
            binding.signaturePath = path

            path?.let {
                binding.metadata =
                    ImageMetadata(internalStorageManager.getLastModifiedTimestamp(path))
            }

            viewModel.updateSignatureError()
        })
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        when (requestCode) {
            Constants.REQUEST_CODE_SIGNATURE -> {
                if (resultCode == Activity.RESULT_OK) {
                    data?.getStringExtra(Constants.EXTRA_SIGNATURE_FILE_PATH)?.let {
                        viewModel.signaturePath.value =
                            internalStorageManager.getImageAbsoluteFilePath(
                                InternalStorageManager.CACHE, it
                            )
                    }
                }
            }
        }
    }

    private fun handleAddRecord() {
        viewModel.clickedAddRecord.simpleSubscribe {
            val dialog = showAddingRecordProgressDialog()

            Single.fromCallable {
                var newSignaturePath: String? = null

                person?.let { person ->
                    val newSignatureFilename = internalStorageManager.getUniqueFilename()

                    // Trim the bitmap and save the signature to internal storage
                    val croppedBitmap = internalStorageManager.loadSignature(
                        InternalStorageManager.CACHE,
                        person.id,
                        signatureFilename
                    ).cropped()

                    var saved = false
                    croppedBitmap?.let { bitmap ->
                        saved = !internalStorageManager.saveSignature(
                            InternalStorageManager.INTERNAL,
                            person.id,
                            bitmap,
                            newSignatureFilename
                        ).isEmpty()
                    }

                    var deleted = false
                    if (saved) {
                        // Delete the file from the cache
                        deleted = internalStorageManager.delete(
                            internalStorageManager.getImageAbsoluteFilePath(
                                InternalStorageManager.CACHE,
                                person.id,
                                signatureFilename
                            )
                        )
                    }

                    if (deleted) {
                        // Get a reference to the new internal file
                        newSignaturePath = internalStorageManager.getImageAbsoluteFilePath(
                            InternalStorageManager.INTERNAL,
                            person.id,
                            newSignatureFilename
                        )
                    }
                }

                newSignaturePath
            }.simpleSubscribe { path ->
                viewModel.result.simpleSubscribe {
                    dialog.cancel()
                    activity?.finish()
                }

                if (path == null) {
                    dialog.cancel()
                    context?.toast(Throwable())
                } else {
                    // At this point, the person detail and the live data do not have synced
                    // file paths, but we don't care since the activity will be destroyed
                    viewModel.setSignatureFilePath(path)
                    viewModel.addPersonDetail()
                }
            }
        }
    }

    private fun showDatePicker() {
        if (context == null) {
            return
        }

        val date = Calendar.getInstance()
        date.timeInMillis = viewModel.dateTimestamp

        DatePickerDialog(
            context!!,
            DatePickerDialog.OnDateSetListener { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                date.set(year, month, dayOfMonth)
                viewModel.dateTimestamp = date.timeInMillis
            }, date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    private fun showTimePicker() {
        if (context == null) {
            return
        }

        val date = Calendar.getInstance()
        date.timeInMillis = viewModel.timeTimestamp

        TimePickerDialog(
            context!!,
            TimePickerDialog.OnTimeSetListener { _: TimePicker, hourOfDay: Int, minute: Int ->
                date.set(Calendar.HOUR_OF_DAY, hourOfDay)
                date.set(Calendar.MINUTE, minute)
                viewModel.timeTimestamp = date.timeInMillis
            },
            date.get(Calendar.HOUR_OF_DAY),
            date.get(Calendar.MINUTE),
            false
        ).show()
    }

    private fun showSignatureActivity() {
        val intent = Intent(context, SignatureActivity::class.java)
        intent.putExtra(Constants.EXTRA_PERSON, person)
        intent.putExtra(Constants.EXTRA_SIGNATURE_FILE_NAME, signatureFilename)
        startActivityForResult(intent, Constants.REQUEST_CODE_SIGNATURE)
    }
}
