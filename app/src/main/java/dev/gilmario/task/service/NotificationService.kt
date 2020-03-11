package dev.gilmario.task.service

import android.app.IntentService
import android.content.Intent
import android.util.Log
import dev.gilmario.task.util.NotificationUtil
import dev.gilmario.task.view.RegisterActivity

class NotificationService: IntentService("Post "){
    private val flag = "LAST_POST_ID"

    override fun onHandleIntent(intent: Intent?) {

        try {
                val intent = Intent(this, RegisterActivity::class.java)
                NotificationUtil.show(this, 100, "adfad", "asdfad" , "aaaa", intent);
            }
         catch (e: Exception) {
        }

    }

}