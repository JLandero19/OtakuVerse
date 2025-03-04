package com.example.otakuverse.localdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.otakuverse.datamodel.Anime
import com.example.otakuverse.datamodel.Comment

@Database(entities = [Anime::class, Comment::class], version = 5, exportSchema = false)
abstract class OtakuverseDatabase : RoomDatabase() {

    abstract fun animeDAO(): AnimeDAO
    abstract fun commentDAO(): CommentDAO

    companion object {
        @Volatile
        private var Instance: OtakuverseDatabase? = null
        fun getDatabase(context: Context): OtakuverseDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, OtakuverseDatabase::class.java, "otaku_db")
                    .fallbackToDestructiveMigration() // Evitar errores si hay cambios en el esquema
                    .build()
                    .also { Instance = it }
            }
        }
    }
}
