package com.yveschiong.personalrecordbook.ui.persondetail

import android.databinding.DataBindingUtil
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import com.yveschiong.data.storage.InternalStorageManager
import com.yveschiong.personalrecordbook.BR
import com.yveschiong.personalrecordbook.R
import com.yveschiong.personalrecordbook.common.base.BindableRecyclerViewHolder
import com.yveschiong.personalrecordbook.common.listeners.OnAdapterViewClicked
import com.yveschiong.personalrecordbook.databinding.ListItemPersonDetailBinding
import com.yveschiong.personalrecordbook.entities.PersonDetail
import java.util.*

class PeopleDetailsAdapter(
    private val clicked: OnAdapterViewClicked<PersonDetail>,
    private val internalStorageManager: InternalStorageManager
) : RecyclerView.Adapter<BindableRecyclerViewHolder>() {

    private val personDetails = ArrayList<PersonDetail>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): BindableRecyclerViewHolder {
        val layoutInflater = LayoutInflater.from(viewGroup.context)
        val binding = DataBindingUtil.inflate<ListItemPersonDetailBinding>(layoutInflater, R.layout.list_item_person_detail, viewGroup, false)
        binding.listener = clicked
        binding.manager = internalStorageManager
        return BindableRecyclerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BindableRecyclerViewHolder, i: Int) {
        holder.binding.setVariable(BR.detail, personDetails[i])
    }

    override fun getItemCount(): Int {
        return personDetails.size
    }

    fun setData(personDetails: List<PersonDetail>) {
        this.personDetails.clear()
        this.personDetails.addAll(personDetails)
        notifyDataSetChanged()
    }
}
