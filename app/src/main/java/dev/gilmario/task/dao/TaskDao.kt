package dev.gilmario.task.dao

import android.arch.persistence.room.*
import dev.gilmario.task.model.Priority
import dev.gilmario.task.model.Task
import dev.gilmario.task.model.User

@Dao
interface TaskDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(task: Task)

    @Delete
    fun delete(task: Task)

    @Update
    fun update(task : Task)

    @Query("SELECT * FROM tbl_task")
    fun selectAll(): List<Task>

    @Query("SELECT * FROM tbl_task WHERE userId = :id and complete = :complete" )
    fun selectById(id: Int, complete: Int): List<Task>

    @Query("SELECT * FROM tbl_task WHERE id = :id")
    fun selectByIdUser(id: Int): Task

}