package dev.gilmario.task.viewholder

import android.support.v7.widget.RecyclerView
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import dev.gilmario.task.R
import dev.gilmario.task.model.OnTaskListFragmentInteraction
import dev.gilmario.task.model.PriorityCacheConstants
import dev.gilmario.task.model.Task
import java.util.concurrent.TimeoutException

class TaskListViewHolder(itemView: View, val listener : OnTaskListFragmentInteraction) : RecyclerView.ViewHolder(itemView) {

    private val  textViewDescription : TextView = itemView.findViewById(R.id.txtDescriptionItem)
    private val  textViewPriority : TextView = itemView.findViewById(R.id.txtPriority)
    private val  textViewDuaDate : TextView = itemView.findViewById(R.id.txtDueDate)
    private val  mImageTask : ImageView = itemView.findViewById(R.id.imageTaskTodo)


    fun popularLinha(task : Task) {
        textViewDescription.text = task.descricao.toString()
        textViewPriority.text = PriorityCacheConstants.getPriorityDescription(task.priorityId)
        textViewDuaDate.text = task.duedata.toString()
        if(task.complete==1) {
            mImageTask.setImageResource(R.drawable.ic_done)
        }

        textViewDescription.setOnClickListener({
            listener.OnListinerClick(task.id);
        })

    }

}