package dev.gilmario.task.dao

import android.arch.persistence.room.*
import dev.gilmario.task.model.User

/**
 * Interface que descreve os métodos de acesso aos dados do Room
 */
@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: User)

    @Delete
    fun delete(user: User)

    @Query("SELECT * FROM tbl_user")
    fun selectAll(): List<User>

    @Query("SELECT * FROM tbl_user WHERE id = :id")
    fun selectById(id: Int): User

    @Query("SELECT * FROM tbl_user WHERE name = :name and email = :email ")
    fun selectByNameAndEmail(name: String, email:String): User

    @Query("SELECT * FROM tbl_user WHERE email = :email and password = :senha ")
    fun selectByEmailPassword(email: String, senha:String): User

    @Query("DELETE FROM tbl_user")
    fun delete()

}
