package dev.gilmario.task.receivers

import android.annotation.TargetApi

import android.os.Build

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v4.app.NotificationCompat
import android.R
import dev.gilmario.task.view.TaskListFragment


class AlarmReceiver : BroadcastReceiver() {

    @TargetApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context?, intent: Intent?) {
        setNotification(context)

    }
    private fun setNotification(context: Context?) {
        val builder = NotificationCompat.Builder(context)
            .setSmallIcon(R.drawable.radiobutton_off_background) //set icon for notification
            .setContentTitle("Lista de atividades Pendentes")
            .setContentText("Atenção para finaliza-las!")
            .setSmallIcon(R.drawable.ic_dialog_alert
            )
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT) //set priority of notification


        val notificationIntent = Intent(context, TaskListFragment::class.java)

        notificationIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        //notification message will get at NotificationView

        val pendingIntent = PendingIntent.getActivity(
            context, 0, notificationIntent,
            PendingIntent.FLAG_UPDATE_CURRENT
        )
        builder.setContentIntent(pendingIntent)

        // Add as notification
        val notificationManager: NotificationManager =
            context?.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        notificationManager!!.notify(0, builder.build())
    }


}