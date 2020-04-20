package dev.gilmario.task.view

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import dev.gilmario.task.R
import dev.gilmario.task.adapter.TaskListAdapter
import dev.gilmario.task.business.TaskBusiness
import dev.gilmario.task.constants.TaskConstants
import dev.gilmario.task.model.OnTaskListFragmentInteraction
import dev.gilmario.task.model.Task
import dev.gilmario.task.util.SecurityPreferences


class TaskListFragment : Fragment(), View.OnClickListener {


    private lateinit var mContext : Context
    private lateinit var mRecycler : RecyclerView
    private lateinit var mSecurityPreferences: SecurityPreferences
    private lateinit var mTaskListBusiness : TaskBusiness
    private var mTaskFilter : Int = 0;
    private lateinit var mTaskListFragment : OnTaskListFragmentInteraction

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        if(arguments != null) {
            mTaskFilter = arguments!!.getInt(TaskConstants.TASKFILTER.KEY)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_task_list, container, false)
        mContext =  rootView.context
        mRecycler =    rootView.findViewById<RecyclerView>(R.id.recycleTaskList)
        mTaskListBusiness = TaskBusiness(mContext)
        mSecurityPreferences = SecurityPreferences(mContext)

        mTaskListFragment = object : OnTaskListFragmentInteraction {

            override fun OnListinerClick(taskId: Int) {
                val bundle : Bundle = Bundle()
                bundle.putInt(TaskConstants.BUNDLE.TASKID, taskId)

                val intent = Intent(mContext, TaskFormActivity::class.java)
                intent.putExtras(bundle)

                startActivity(intent)
            }
        }

        mRecycler.adapter = TaskListAdapter(mutableListOf(), mTaskListFragment)
        mRecycler.layoutManager = LinearLayoutManager(mContext)

        rootView.findViewById<FloatingActionButton>(R.id.btnFloatAddTask).setOnClickListener(this)
        return rootView
    }

    override fun onResume() {
        super.onResume()
        loadRecycle()
    }

    private fun loadRecycle() {
        val userId = mSecurityPreferences.getStoredString(TaskConstants.KEY.USER_ID).toInt()
        val taskListByUser = mTaskListBusiness.getListTaskByUser(userId, mTaskFilter)
        mRecycler.adapter = TaskListAdapter(taskListByUser, mTaskListFragment)
    }

    override fun onClick(view: View?) {
        if(view?.id == R.id.btnFloatAddTask)  {
            carregarPopupInsert();
        }
    }

    fun carregarPopupInsert() {
        startActivity(Intent(context, TaskFormActivity::class.java))
    }

    companion object {
        fun newInstance(taskFilter : Int): TaskListFragment {
            val args : Bundle = Bundle()
            args.putInt(TaskConstants.TASKFILTER.KEY, taskFilter)
            val fragment = TaskListFragment()
            fragment.arguments = args
            return fragment
        }
    }

}
