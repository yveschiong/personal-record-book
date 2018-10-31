package com.yveschiong.personalrecordbook.ui.people

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.yveschiong.personalrecordbook.R
import com.yveschiong.personalrecordbook.common.BaseFragment
import com.yveschiong.personalrecordbook.common.OnAdapterViewClicked
import com.yveschiong.personalrecordbook.databinding.PeopleFragmentBinding
import com.yveschiong.personalrecordbook.entities.Person
import dagger.android.support.AndroidSupportInjection
import javax.inject.Inject

class PeopleFragment : BaseFragment<PeopleFragmentBinding>() {

    @Inject
    lateinit var viewModelFactory: PeopleViewModelFactory

    private var viewModel: PeopleViewModel? = null

    private var adapter: PeopleAdapter? = null

    companion object {

        fun newInstance(): PeopleFragment {
            return PeopleFragment()
        }
    }

    override val layoutId: Int
        get() = R.layout.people_fragment

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(PeopleViewModel::class.java)
        binding.vm = viewModel
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter = PeopleAdapter(object : OnAdapterViewClicked<Person> {
            override fun onClicked(data: Person) {
                TODO("not implemented")
            }
        })

        binding.recyclerView.setEmptyView(binding.emptyView)

        binding.recyclerView.layoutManager = LinearLayoutManager(context)
        binding.recyclerView.adapter = adapter
    }
}
