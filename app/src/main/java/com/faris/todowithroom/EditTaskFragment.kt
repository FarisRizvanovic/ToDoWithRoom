package com.faris.todowithroom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.faris.todowithroom.databinding.FragmentEditTaskBinding
import com.faris.todowithroom.databinding.FragmentTasksBinding

class EditTaskFragment : Fragment() {

    private var _binding: FragmentEditTaskBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditTaskBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dao = TaskDatabase.getInstance(application).taskDao
        val taskId = EditTaskFragmentArgs.fromBundle(requireArguments()).taskId
        val viewModelFactory = EditTaskViewModelFactory(taskId, dao)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(EditTaskViewModel::class.java)

        viewModel.task.observe(viewLifecycleOwner, Observer {
            binding.taskName.setText(it.taskName)
            binding.taskDone.isChecked = it.taskDone
        })









        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}