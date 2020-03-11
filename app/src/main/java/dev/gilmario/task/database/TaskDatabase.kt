package dev.gilmario.task.database

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.migration.Migration
import dev.gilmario.task.dao.PriorityDao
import dev.gilmario.task.dao.TaskDao
import dev.gilmario.task.dao.UserDao
import dev.gilmario.task.model.Priority
import dev.gilmario.task.model.Task
import dev.gilmario.task.model.User

@Database(entities = arrayOf(User::class, Priority::class, Task::class), version = 6)
abstract class TaskDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun priorityDao(): PriorityDao
    abstract fun taskDao(): TaskDao

}

val MIGRATION_1_2 = object : Migration(1, 2) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "CREATE TABLE `tbl_priority` (`id` INTEGER  NOT NULL, `descricao` TEXT, " +
                    "PRIMARY KEY(`id`))"
        )
    }
}
val MIGRATION_2_3 = object : Migration(2, 3) {
    override fun migrate(database: SupportSQLiteDatabase) {
            database.execSQL("CREATE TABLE `tbl_task` (`id` INTEGER  NOT NULL,`priorityId` INTEGER, `userId` INTEGER,  `complete` INTEGER, `descricao` TEXT, `duedata` TEXT," +
                    "PRIMARY KEY(`id`),  FOREIGN KEY (userId) REFERENCES tbl_user (id) ON DELETE CASCADE, FOREIGN KEY (priorityId) REFERENCES tbl_priority (id) ON DELETE CASCADE )")
    }
 }

val MIGRATION_4_5 = object : Migration(4,5) {
    override fun migrate(database: SupportSQLiteDatabase) {
        database.execSQL(
            "INSERT INTO `tbl_priority` VALUES " +
                    "(0, `BAIXA`)," +
                    "(0, `MEDIA`)," +
                    "(0, `ALTA`)," +
                    "(0, `CRITICA`)"
        )
    }
}

