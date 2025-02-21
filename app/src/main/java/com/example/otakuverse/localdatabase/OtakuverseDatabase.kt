package com.example.otakuverse.localdatabase

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.otakuverse.datamodel.AnimeModel

@Database(entities = [AnimeModel::class], version = 1, exportSchema = false)
abstract class OtakuverseDatabase : RoomDatabase() {
    abstract fun animeDAO(): AnimeDAO

    companion object {
        @Volatile
        private var Instance: OtakuverseDatabase? = null
        fun getDatabase(context: Context): OtakuverseDatabase {
            // if the Instance is not null, return it, otherwise create a new database instance.
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(context, OtakuverseDatabase::class.java, "anime_database")
                    .build()
                    .also { Instance = it }
            }
        }
    }
}