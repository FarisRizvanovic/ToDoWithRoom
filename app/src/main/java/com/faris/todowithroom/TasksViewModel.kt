package com.faris.todowithroom

import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch


class TasksViewModel(private val dao: TaskDao) : ViewModel() {
    var newTaskName = ""

    val tasks = dao.getAll()

    fun addTask() {
        viewModelScope.launch {
            val task = Task()
            task.taskName = newTaskName
            dao.insert(task)
        }
    }

    fun deleteAllEntries(){
        viewModelScope.launch {
            dao.removeAllEntries()
        }
    }
}