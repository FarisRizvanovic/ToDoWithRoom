package com.faris.todowithroom

import androidx.lifecycle.*
import kotlinx.coroutines.launch


class TasksViewModel(private val dao: TaskDao) : ViewModel() {
    var newTaskName = ""

    val tasks = dao.getAll()

    private val _navigateToTask = MutableLiveData<Long?>()
    val navigateToTask: LiveData<Long?>
        get() = _navigateToTask

    fun onTaskClicked(taskId : Long){
        _navigateToTask.value = taskId
    }

    fun onTaskNavigated(){
        _navigateToTask.value = null
    }

    fun addTask() {
        viewModelScope.launch {
            val task = Task()
            task.taskName = newTaskName
            dao.insert(task)
        }
    }

    fun deleteAllEntries() {
        viewModelScope.launch {
            dao.removeAllEntries()
        }
    }
}