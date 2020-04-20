package dev.gilmario.task.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity;
import android.util.Log
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

class TaskFormActivity : AppCompatActivity(), View.OnClickListener, DatePickerDialog.OnDateSetListener {


    private lateinit var mPriorityBusiness: PriorityBusiness
    private lateinit var mTaskBusiness: TaskBusiness
    private lateinit var mSecurityPreferences: SecurityPreferences
    private lateinit var listaPriority :  List<Priority>
    private var mlistaIds : MutableList<Int> = mutableListOf()
    private var mtaskId : Int = 0

    private val mSimpleDateFormat : SimpleDateFormat = SimpleDateFormat("dd/MM/yyyy")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_task_form)
        setSupportActionBar(toolbar)
        mPriorityBusiness = PriorityBusiness(this)
        mTaskBusiness = TaskBusiness(this)
        mSecurityPreferences = SecurityPreferences(this)
        carregarSpinner()
        setListener()
        verifyIfExistDataForLoad()
    }

    fun verifyIfExistDataForLoad() {
       val bundle = intent.extras
        if(bundle != null) {
            mtaskId = bundle.getInt(TaskConstants.BUNDLE.TASKID)
            val task = mTaskBusiness.getList(mtaskId)
            if(task != null ) {
                editDescription.setText(task.descricao.toString())
                btnDate.setText(task.duedata)
                checkComplete.setText(task.complete.toString())
                spinnerPriority.setSelection(getIndex(task.priorityId));
            }
        }
    }

    private fun getIndex(indexId :Int?) : Int{
        var index = 0;
        for (i in 0..listaPriority.size) {
            if(listaPriority[i].id == indexId) {
                index =i
                break;
            }
        }
        return index
    }


    override fun onClick(view: View?) {
        if(view?.id == R.id.btnDate)  {
            carregarPopupDate();
        }else if (view?.id == R.id.btnSaveTask) {
            saveTask()
        }
    }

    private fun saveTask() {
        try {
            val description = editDescription.text.toString()
            val complete = checkComplete.isChecked
            val duadate = btnDate.text.toString()
            val userId = mSecurityPreferences.getStoredString(TaskConstants.KEY.USER_ID).toInt()


            val priorityId = mlistaIds[spinnerPriority.selectedItemPosition]

            var completeId :Int = 0
             if(complete) {
                 completeId = 1
             }


            if(mtaskId==0) {
                mTaskBusiness.insert(0, priorityId, userId, completeId, description, duadate)
                Toast.makeText(this, "Dados Salvo com sucesso!!!", Toast.LENGTH_LONG).show()

            }else {
                mTaskBusiness.update(mtaskId, priorityId, userId, completeId, description, duadate)
                Toast.makeText(this, "Dados Alterado com sucesso!!!", Toast.LENGTH_LONG).show()

            }

            finish()
        }catch (e: Exception) {
            Toast.makeText(this, "Algo inesperado!!!", Toast.LENGTH_LONG).show()
        }

    }

    override fun onResume() {
        super.onResume()
    }

    fun setListener() {
        btnDate.setOnClickListener(this)
        btnSaveTask.setOnClickListener(this)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMoth: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, dayOfMoth)
        btnDate.text =  mSimpleDateFormat.format(calendar.time)
    }

    fun carregarPopupDate() {
        val c = Calendar.getInstance()
        val year = c.get(Calendar.YEAR)
        val month = c.get(Calendar.MONTH)
        val dayOfMonth = c.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this, this, year, month, dayOfMonth).show()
    }

    fun carregarSpinner() {
        listaPriority =  mPriorityBusiness.getList();
        val listaStringDescricao = listaPriority.map { it.descricao }
        mlistaIds = listaPriority.map { it.id }.toMutableList()

        val adpter = ArrayAdapter<String> (this, android.R.layout.simple_spinner_dropdown_item, listaStringDescricao)
        spinnerPriority.adapter = adpter
    }

}
