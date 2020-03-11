package dev.gilmario.task.constants

import java.security.Key

class TaskConstants {
    object KEY {
        val USER_ID = "userId"
        val USER_NAME = "userName"
        val USER_EMAIL = "userEmail"
    }
    object TASKFILTER {
        val KEY = "taskFilterKey"
        val NEW = 2
        val COMPLETE = 1
        val TOO = 0
    }
    object BUNDLE {
        val TASKID= "filterTaskId"
    }

}