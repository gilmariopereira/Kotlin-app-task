package dev.gilmario.task.model


import android.arch.persistence.room.ColumnInfo
import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "tbl_user")
data class User(

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    @ColumnInfo(name = "name")
    var name: String?,

    @ColumnInfo(name = "email")
    var email: String?,

    @ColumnInfo(name = "password")
    var password: String?


)