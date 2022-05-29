package com.faris.todowithroom

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TaskDao {
    /*
    this method is for inserting tasks into the database table
    we can also give it a  collection of task like task : List<Task> to save
     */
    @Insert
    suspend fun insert(task: Task)

    /*
    This will update the task with the same taskId (primary key)
     */
    @Update
    suspend fun update(task: Task)

    /*
    This will delete a task with the matching taskId (primary key)
     */
    @Delete
    suspend fun delete(task: Task)

    /*
    @Query lets you define a SQL statement (using: SELECT, INSERT, UPDATE or DELETE)
    This one that I specified will return a task as a live data object with
    the taskId that is passed to it
     */
    @Query("SELECT * FROM task_table WHERE taskId = :taskId")
    fun get(taskId : Long): LiveData<Task>

    /*
    This query return a live data list of all tasks in descending order
     */
    @Query("SELECT * FROM task_table ORDER BY taskId DESC")
    fun getAll() : LiveData<List<Task>>

    /*
    Drop the table contents
     */
    @Query("DELETE FROM task_table")
    suspend fun removeAllEntries()

}