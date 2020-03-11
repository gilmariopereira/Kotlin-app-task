package dev.gilmario.task.database

import android.arch.persistence.room.Room
import android.content.Context

/**
 * Classe reponsável por gerenciar a instância do Room
 */
object RoomManager {

    /**
     * Método que retorna uma instância única do banco de dados (WeatherDatabase)
     */
    fun instance(context: Context) = Room.databaseBuilder(
        context,
        TaskDatabase::class.java, "db")
        .fallbackToDestructiveMigration()
        .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_4_5)
        .allowMainThreadQueries()
        .build()
}