package com.yveschiong.personalrecordbook.ui.addperson

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.yveschiong.personalrecordbook.R
import com.yveschiong.personalrecordbook.common.base.BaseFragment
import com.yveschiong.personalrecordbook.databinding.FragmentAddPersonBinding
import dagger.android.support.AndroidSupportInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AddPersonFragment : BaseFragment<FragmentAddPersonBinding>() {

    @Inject
    lateinit var viewModelFactory: AddPersonViewModelFactory

    lateinit var viewModel: AddPersonViewModel

    companion object {

        fun newInstance(): AddPersonFragment {
            return AddPersonFragment()
        }
    }

    override val layoutId: Int
        get() = R.layout.fragment_add_person

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(AddPersonViewModel::class.java)
        binding.vm = viewModel

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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}
