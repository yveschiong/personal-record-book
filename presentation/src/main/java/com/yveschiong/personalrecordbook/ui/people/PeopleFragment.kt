package com.yveschiong.personalrecordbook.ui.people

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import com.yveschiong.personalrecordbook.R
import com.yveschiong.personalrecordbook.common.base.BaseFragment
import com.yveschiong.personalrecordbook.common.listeners.OnAdapterViewClicked
import com.yveschiong.personalrecordbook.common.utils.view.Refreshable
import com.yveschiong.personalrecordbook.databinding.FragmentPeopleBinding
import com.yveschiong.personalrecordbook.entities.Person
import dagger.android.support.AndroidSupportInjection
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class PeopleFragment : BaseFragment<FragmentPeopleBinding>(), Refreshable {

    @Inject
    lateinit var viewModelFactory: PeopleViewModelFactory

    lateinit var viewModel: PeopleViewModel

    lateinit var adapter: PeopleAdapter

    companion object {

        fun newInstance(): PeopleFragment {
            return PeopleFragment()
        }
    }

    override val layoutId: Int
        get() = R.layout.fragment_people

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PeopleViewModel::class.java)
        binding.vm = viewModel

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
            refresh()
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = PeopleAdapter(object : OnAdapterViewClicked<Person> {
            override fun onClicked(data: Person) {
                // Do nothing for now.
            }
        })

        binding.recyclerView.setEmptyView(binding.emptyView)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
    }

    override fun refresh() {
        viewModel.fetch()
    }
}
