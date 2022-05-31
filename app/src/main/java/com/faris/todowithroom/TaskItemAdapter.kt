package com.faris.todowithroom

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView

class TaskItemAdapter : ListAdapter<Task, TaskItemAdapter.TaskItemViewHolder>(TaskDiffItemCallback()) {

    class TaskItemViewHolder(rootView: CardView) : RecyclerView.ViewHolder(rootView) {

        private val taskName  = rootView.findViewById<TextView>(R.id.task_name)
        private val taskDone = rootView.findViewById<CheckBox>(R.id.task_done)
        companion object {
            fun inflateFrom(parent: ViewGroup): TaskItemViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val view = layoutInflater
                    .inflate(R.layout.task_item, parent, false) as CardView
                return TaskItemViewHolder(view)
            }
        }

        fun bind(item: Task) {
            taskName.text = item.taskName
            taskDone.isChecked = item.taskDone
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskItemViewHolder {
        return TaskItemViewHolder.inflateFrom(parent)
    }

    override fun onBindViewHolder(holder: TaskItemViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }



}