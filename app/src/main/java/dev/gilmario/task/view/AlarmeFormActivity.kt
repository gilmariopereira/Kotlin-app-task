package dev.gilmario.task.view

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.util.Log
import android.view.ContextMenu
import android.view.View
import android.widget.ArrayAdapter
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import dev.gilmario.task.R
import dev.gilmario.task.business.PriorityBusiness
import dev.gilmario.task.business.TaskBusiness
import dev.gilmario.task.business.UserBusiness
import dev.gilmario.task.constants.TaskConstants
import dev.gilmario.task.model.Priority
import dev.gilmario.task.util.SecurityPreferences

import kotlinx.android.synthetic.main.activity_task_form.*
import kotlinx.android.synthetic.main.content_task_form.*
import kotlinx.android.synthetic.main.row_task_list.*
import java.text.SimpleDateFormat
import java.util.*

class AlarmeFormActivity : AppCompatActivity() {


    private lateinit var mTaskBusiness: TaskBusiness
    private lateinit var mSecurityPreferences: SecurityPreferences
    private val mSimpleDateFormat : SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarme_form)
        setSupportActionBar(toolbar)
        mTaskBusiness = TaskBusiness(this)
        mSecurityPreferences = SecurityPreferences(this)
        setListener(this);
    }

    private fun saveTask() {
        try {
             val duadate = btnDate.text.toString()
            val userId = mSecurityPreferences.getStoredString(TaskConstants.KEY.USER_ID).toInt()

            if(userId==0) {
             //   mTaskBusiness.insert(0, priorityId, userId, completeId, description, duadate)
                Toast.makeText(this, "Dados Salvo com sucesso!!!", Toast.LENGTH_LONG).show()

            }else {
               // mTaskBusiness.update(mtaskId, priorityId, userId, completeId, description, duadate)
                Toast.makeText(this, "Dados Alterado com sucesso!!!", Toast.LENGTH_LONG).show()
            }
            finish()
        }catch (e: Exception) {
            Toast.makeText(this, "Algo inesperado!!!", Toast.LENGTH_LONG).show()
        }

    }

    fun setListener(cxt : Context) {
       // btnSaveTask.setOnClickListener(cxt)
    }



}
