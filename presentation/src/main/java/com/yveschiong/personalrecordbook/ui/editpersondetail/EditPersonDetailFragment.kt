package com.yveschiong.personalrecordbook.ui.editpersondetail

import android.app.Dialog
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.view.Window
import com.yveschiong.personalrecordbook.R
import com.yveschiong.personalrecordbook.common.Constants
import com.yveschiong.personalrecordbook.common.base.BaseDialogFragment
import com.yveschiong.personalrecordbook.databinding.FragmentEditPersonDetailBinding
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject



class EditPersonDetailFragment : BaseDialogFragment<FragmentEditPersonDetailBinding>() {

    @Inject
    lateinit var viewModelFactory: EditPersonDetailViewModelFactory

    lateinit var viewModel: EditPersonDetailViewModel

    companion object {

        fun newInstance(): EditPersonDetailFragment {
            return EditPersonDetailFragment()
        }
    }

    override val layoutId: Int
        get() = R.layout.fragment_edit_person_detail

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel =
            ViewModelProviders.of(this, viewModelFactory).get(EditPersonDetailViewModel::class.java)
        binding.vm = viewModel

        viewModel.detail = arguments?.getParcelable(Constants.EXTRA_PERSON_DETAIL)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)

        // request a window without the title
        dialog.window?.requestFeature(Window.FEATURE_NO_TITLE)
        return dialog
    }
}