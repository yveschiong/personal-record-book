package com.yveschiong.personalrecordbook.ui.addpersondetail

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import android.widget.TimePicker
import com.yveschiong.personalrecordbook.R
import com.yveschiong.personalrecordbook.common.Constants
import com.yveschiong.personalrecordbook.common.base.BaseFragment
import com.yveschiong.personalrecordbook.databinding.FragmentAddPersonDetailBinding
import com.yveschiong.personalrecordbook.entities.Person
import dagger.android.support.AndroidSupportInjection
import java.util.*
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

        viewModel.result.simpleSubscribe{ activity?.finish() }
        viewModel.clickedDate.simpleSubscribe { showDatePicker() }
        viewModel.clickedTime.simpleSubscribe { showTimePicker() }
    }

    private fun showDatePicker() {
        if (context == null) {
            return
        }

        val date = Calendar.getInstance()
        date.timeInMillis = viewModel.dateTimestamp

        DatePickerDialog(context!!,
            DatePickerDialog.OnDateSetListener { _: DatePicker, year: Int, month: Int, dayOfMonth: Int ->
                date.set(year, month, dayOfMonth)
                viewModel.setDate(date.timeInMillis)
            }, date.get(Calendar.YEAR), date.get(Calendar.MONTH), date.get(Calendar.DAY_OF_MONTH)).show()
    }

    private fun showTimePicker() {
        if (context == null) {
            return
        }

        val date = Calendar.getInstance()
        date.timeInMillis = viewModel.timeTimestamp

        TimePickerDialog(context!!, TimePickerDialog.OnTimeSetListener { _: TimePicker, hourOfDay: Int, minute: Int ->
            date.set(Calendar.HOUR_OF_DAY, hourOfDay)
            date.set(Calendar.MINUTE, minute)
            viewModel.setTime(date.timeInMillis)
        }, date.get(Calendar.HOUR_OF_DAY), date.get(Calendar.MINUTE), false).show()
    }
}
