package dev.gilmario.task.business

import android.content.Context
import android.util.Log
import dev.gilmario.task.constants.TaskConstants
import dev.gilmario.task.dao.TaskDao
import dev.gilmario.task.database.RoomManager
import dev.gilmario.task.model.Task
import dev.gilmario.task.model.User
import dev.gilmario.task.util.SecurityPreferences
import dev.gilmario.task.util.ValidationException
import java.lang.Exception

class TaskBusiness(val context: Context) {

    private val taskDao = RoomManager.instance(context).taskDao()


    fun getList(userId : Int) : Task {
        return taskDao.selectByIdUser(userId)
    }

    fun getListTaskByUser(userId : Int, mTaskfilter : Int) : List<Task> {
        return taskDao.selectById(userId, mTaskfilter);
    }

    fun insert(id:Int,  priorityId:Int,  userId:Int, complete:Int,  descricao:String, duadata: String) {
        try {
            taskDao.insert(Task(id, priorityId, userId, complete, descricao, duadata))
        }catch (e: Exception) {
            throw e
        }
    }

    fun update(id:Int,  priorityId:Int,  userId:Int, complete:Int,  descricao:String, duadata: String) {
        try {
            taskDao.update(Task(id, priorityId, userId, complete, descricao, duadata))
        }catch (e: Exception) {
            throw e
        }
    }



}