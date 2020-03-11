package dev.gilmario.task.business

import android.content.Context
import dev.gilmario.task.constants.TaskConstants
import dev.gilmario.task.database.RoomManager
import dev.gilmario.task.model.Task
import dev.gilmario.task.model.User
import dev.gilmario.task.util.SecurityPreferences
import dev.gilmario.task.util.ValidationException
import java.lang.Exception

class UserBusiness(val context: Context) {

    private val userDao = RoomManager.instance(context).userDao()
    private val mSecurityPreferences: SecurityPreferences = SecurityPreferences(context)


    fun validUser(email:String, senha:String) : Boolean {
        var flag : Boolean = false;
        val user : User = userDao.selectByEmailPassword(email, senha);
        userDao.selectAll();

        if(user!=null) {
            flag = true;
            mSecurityPreferences.storeString(TaskConstants.KEY.USER_ID, user.id.toString())
            mSecurityPreferences.storeString(TaskConstants.KEY.USER_NAME, user.name.toString())
            mSecurityPreferences.storeString(TaskConstants.KEY.USER_EMAIL, user.email.toString())
        }
        return flag;
    }


    fun getListUsers() : List<User> {
        return userDao.selectAll()
    }

    fun insert(id:Int, name:String, email: String, password:String) {
        try {
             if(name == "" || email == "" || password == "" ) {
                 throw ValidationException("Informe todos os campos")
             }
             userDao.insert(User(id, name, email, password))
             mSecurityPreferences.storeString(TaskConstants.KEY.USER_ID, id.toString())
             mSecurityPreferences.storeString(TaskConstants.KEY.USER_NAME, name)
             mSecurityPreferences.storeString(TaskConstants.KEY.USER_EMAIL, email)
        }catch (e: Exception) {
            throw e
        }
    }

    fun deleteAll() {
        return userDao.delete()
    }



}