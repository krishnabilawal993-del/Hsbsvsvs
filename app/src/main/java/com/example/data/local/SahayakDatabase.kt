package com.example.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [PortalItemEntity::class],
    version = 1,
    exportSchema = false
)
abstract class SahayakDatabase : RoomDatabase() {
    abstract fun portalItemDao(): PortalItemDao

    companion object {
        @Volatile
        private var INSTANCE: SahayakDatabase? = null

        fun getInstance(context: Context): SahayakDatabase {
            return INSTANCE ?: synchronized(this) {
                INSTANCE ?: Room.databaseBuilder(
                    context.applicationContext,
                    SahayakDatabase::class.java,
                    "gujarat_sahayak_local.db"
                )
                    .fallbackToDestructiveMigration(dropAllTables = true)
                    .build()
                    .also { INSTANCE = it }
            }
        }
    }
}
