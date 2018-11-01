package com.yveschiong.personalrecordbook.ui.people

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.yveschiong.personalrecordbook.BR
import com.yveschiong.personalrecordbook.R
import com.yveschiong.personalrecordbook.common.base.BindableRecyclerViewHolder
import com.yveschiong.personalrecordbook.common.listeners.OnAdapterViewClicked
import com.yveschiong.personalrecordbook.databinding.ListItemPersonBinding
import com.yveschiong.personalrecordbook.entities.Person
import java.util.*

class PeopleAdapter(private val clicked: OnAdapterViewClicked<Person>) : RecyclerView.Adapter<BindableRecyclerViewHolder>() {

    private val people = ArrayList<Person>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): BindableRecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding = DataBindingUtil.inflate<ListItemPersonBinding>(layoutInflater, R.layout.list_item_person, viewGroup, false)
        binding.listener = clicked
        return BindableRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindableRecyclerViewHolder, i: Int) {
        holder.binding.setVariable(BR.person, people[i])
    }

    override fun getItemCount(): Int {
        return people.size
    }

    fun setData(people: List<Person>) {
        this.people.clear()
        this.people.addAll(people)
        notifyDataSetChanged()
    }
}
