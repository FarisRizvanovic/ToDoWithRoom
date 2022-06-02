package com.faris.todowithroom

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class EditTaskViewModel(taskId: Long, val dao: TaskDao) : ViewModel() {
    val task = dao.get(taskId)

    private val _navigateToList = MutableLiveData<Boolean>(false)
    val navigateToList: LiveData<Boolean>
        get() = _navigateToList

    fun updateTask() {
        viewModelScope.launch {
            _navigateToList.value = true
            dao.update(task.value!!)
        }
    }

    fun deleteTask() {
        viewModelScope.launch {
            _navigateToList.value = true
            dao.delete(task.value!!)
        }
    }

    fun onNavigatedToList() {
        _navigateToList.value = false
    }
}