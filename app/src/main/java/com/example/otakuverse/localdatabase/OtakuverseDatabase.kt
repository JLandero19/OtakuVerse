package com.example.otakuverse.localdatabase

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.otakuverse.datamodel.AnimeModel

@Database(entities = [AnimeModel::class], version = 1, exportSchema = false)
abstract class OtakuverseDatabase : RoomDatabase() {

    abstract fun animeDAO(): AnimeDAO

    companion object {
        @Volatile
        private var INSTANCE: OtakuverseDatabase? = null
        fun getDatabase(context: Context): OtakuverseDatabase {
            return INSTANCE ?: synchronized(this) {
                try {
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        OtakuverseDatabase::class.java,
                        "anime_database"
                    )
                    .fallbackToDestructiveMigration()  // Esto permitirá destruir y recrear la base de datos si hay un problema
                    .build().also { INSTANCE = it }

                    Log.d("DATABASEROOM", "Base de datos creada: ${instance.isOpen}")

                    INSTANCE = instance
                    instance
                } catch (e: Exception) {
                    Log.e("DATABASEROOM", "Error al crear la base de datos: ${e.message}")
                    throw e  // Lanza la excepción para que se pueda detectar en el LogCat
                }
            }

//            return INSTANCE ?: synchronized(this) {
//                Room.databaseBuilder(context, OtakuverseDatabase::class.java, "otaku_db")
//                    .fallbackToDestructiveMigration() // Cuando se realice un cambio en la base de datos, se borran los datos
//                    .build()
//                    .also { INSTANCE = it }
//            }
        }

    }
}
