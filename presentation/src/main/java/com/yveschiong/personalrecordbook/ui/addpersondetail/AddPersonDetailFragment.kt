package com.yveschiong.personalrecordbook.ui.addpersondetail

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.yveschiong.personalrecordbook.R
import com.yveschiong.personalrecordbook.common.Constants
import com.yveschiong.personalrecordbook.common.base.BaseFragment
import com.yveschiong.personalrecordbook.databinding.FragmentAddPersonDetailBinding
import com.yveschiong.personalrecordbook.entities.Person
import dagger.android.support.AndroidSupportInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddPersonDetailFragment : BaseFragment<FragmentAddPersonDetailBinding>() {

    @Inject
    lateinit var viewModelFactory: AddPersonDetailViewModelFactory

    lateinit var viewModel: AddPersonDetailViewModel

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
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AddPersonDetailViewModel::class.java)
        binding.vm = viewModel

        arguments?.getParcelable<Person>(Constants.EXTRA_PERSON)?.let {
            viewModel.personDetail.personId = it.id
        }

        viewModel.result
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                activity?.finish()
            }, {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            })
            .addToDisposables()
    }
}
