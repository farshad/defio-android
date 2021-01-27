package io.neoattitude.defio

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.neoattitude.defio.data.dao.TokenDao
import io.neoattitude.defio.data.entity.Token

@Database(entities = [Token::class], version = 2, exportSchema = false)
abstract class DefioDatabase : RoomDatabase() {

    abstract fun tokenDao(): TokenDao

    companion object {
        @Volatile
        private var INSTANCE: DefioDatabase? = null

        fun getDatabase(
            context: Context
        ): DefioDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    DefioDatabase::class.java,
                    "defio_database"
                )
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}