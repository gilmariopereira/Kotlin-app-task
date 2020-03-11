package dev.gilmario.task.dao

import android.arch.persistence.room.*
import dev.gilmario.task.model.Priority
import dev.gilmario.task.model.User

@Dao
interface PriorityDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(priority: Priority)

    @Delete
    fun delete(priority: Priority)

    @Query("SELECT * FROM tbl_priority")
    fun selectAll(): List<Priority>

}