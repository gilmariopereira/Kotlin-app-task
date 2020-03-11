package dev.gilmario.task.business

import android.content.Context
import android.util.Log
import dev.gilmario.task.constants.TaskConstants
import dev.gilmario.task.dao.TaskDao
import dev.gilmario.task.database.RoomManager
import dev.gilmario.task.model.Priority
import dev.gilmario.task.model.Task
import dev.gilmario.task.model.User
import dev.gilmario.task.util.SecurityPreferences
import dev.gilmario.task.util.ValidationException
import java.lang.Exception

class PriorityBusiness(val context: Context) {

    private val priorityDao = RoomManager.instance(context).priorityDao()

    fun getList() : List<Priority>{
        return priorityDao.selectAll()
    }

    fun insert(id:Int, descricao:String) {
        try {
            priorityDao.insert(Priority(id, descricao))
        }catch (e: Exception) {
            throw e
        }
    }



}