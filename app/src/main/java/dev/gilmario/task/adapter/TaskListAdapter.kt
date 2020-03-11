package dev.gilmario.task.adapter

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.ViewGroup
import dev.gilmario.task.R
import dev.gilmario.task.model.OnTaskListFragmentInteraction
import dev.gilmario.task.model.Task
import dev.gilmario.task.viewholder.TaskListViewHolder

class TaskListAdapter( val mListTaskByUser : List<Task>, val mListener : OnTaskListFragmentInteraction) : RecyclerView.Adapter<TaskListViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, p1: Int): TaskListViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.row_task_list, parent, false)
        return TaskListViewHolder(view, mListener)

    }

    override fun getItemCount(): Int {
        return mListTaskByUser.count()
    }

    override fun onBindViewHolder(holder: TaskListViewHolder, position: Int) {
        val task = mListTaskByUser[position]
        holder.popularLinha(task);
    }


}