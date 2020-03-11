package dev.gilmario.task.model


import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.ForeignKey
import android.arch.persistence.room.ForeignKey.CASCADE
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "tbl_task",
    foreignKeys = [ForeignKey(
        entity = User::class,
        parentColumns = ["id"],
        childColumns = ["userId"],
        onDelete = CASCADE),
        ForeignKey( entity = Priority::class,
        parentColumns = ["id"],
        childColumns = ["priorityId"],
        onDelete = CASCADE)])
data class Task(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "priorityId")
    var priorityId: Int?,

    @ColumnInfo(name = "userId")
    var userId: Int?,

    @ColumnInfo(name = "complete")
    var complete: Int?,

    @ColumnInfo(name = "descricao")
    var descricao: String?,

    @ColumnInfo(name = "duedata")
    var duedata: String?

)