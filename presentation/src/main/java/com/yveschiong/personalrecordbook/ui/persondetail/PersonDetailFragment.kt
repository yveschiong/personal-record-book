package com.yveschiong.personalrecordbook.ui.persondetail

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.yveschiong.personalrecordbook.R
import com.yveschiong.personalrecordbook.common.Constants
import com.yveschiong.personalrecordbook.common.base.BaseFragment
import com.yveschiong.personalrecordbook.common.listeners.OnAdapterViewClicked
import com.yveschiong.personalrecordbook.databinding.FragmentPersonDetailBinding
import com.yveschiong.personalrecordbook.entities.PersonDetail
import dagger.android.support.AndroidSupportInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.list_person_details.view.*
import javax.inject.Inject

class PersonDetailFragment : BaseFragment<FragmentPersonDetailBinding>() {

    @Inject
    lateinit var viewModelFactory: PersonDetailViewModelFactory

    lateinit var viewModel: PersonDetailViewModel

    lateinit var adapter: PeopleDetailsAdapter

    companion object {

        fun newInstance(): PersonDetailFragment {
            return PersonDetailFragment()
        }
    }

    override val layoutId: Int
        get() = R.layout.fragment_person_detail

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PersonDetailViewModel::class.java)
        binding.vm = viewModel
        binding.person = arguments?.getParcelable(Constants.EXTRA_PERSON)

        viewModel.result
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({
                adapter.setData(it)
            }, {
                Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show()
            })
            .addToDisposables()

        if (savedInstanceState == null) {
            // We want to make a fetch the very first time the activity has been created
            binding.person?.id?.let {
                viewModel.fetch(it)
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = PeopleDetailsAdapter(object : OnAdapterViewClicked<PersonDetail> {
            override fun onClicked(data: PersonDetail) {

            }
        })

        binding.listPersonDetails.recyclerView.setEmptyView(binding.listPersonDetails.emptyView)

        binding.listPersonDetails.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.listPersonDetails.recyclerView.adapter = adapter
    }
}
