package com.faris.todowithroom

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.faris.todowithroom.databinding.FragmentTasksBinding

class TasksFragment : Fragment() {

    private var _binding: FragmentTasksBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTasksBinding.inflate(layoutInflater, container, false)
        val view = binding.root

        val application = requireNotNull(this.activity).application
        val dao = TaskDatabase.getInstance(application).taskDao
        val viewModelFactory = TaskViewModelFactory(dao)
        val viewModel = ViewModelProvider(this, viewModelFactory)
            .get(TasksViewModel::class.java)

        val adapter = TaskItemAdapter()
        binding.tasksList.adapter = adapter

        viewModel.tasks.observe(viewLifecycleOwner, Observer {
            it.let {
                adapter.data = it
            }
        })

        binding.saveButton.setOnClickListener {
            if (binding.taskName.text.toString() != "") {
                viewModel.newTaskName = binding.taskName.text.toString()
                viewModel.addTask()
                binding.taskName.text = null
            } else {
                Toast.makeText(context, "Task name blank!", Toast.LENGTH_SHORT).show()
            }

        }

        binding.deleteEntries.setOnClickListener {
            viewModel.deleteAllEntries()
        }

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}