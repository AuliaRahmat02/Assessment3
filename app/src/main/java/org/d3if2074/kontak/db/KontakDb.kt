package org.d3if2074.kontak.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [KontakEntity::class], version = 1, exportSchema = false)
abstract class KontakDb: RoomDatabase(){
    abstract val dao:KontakDao

    companion object{

        @Volatile
        private var INSTANCE:KontakDb? = null

        fun getInstance(context: Context): KontakDb {
            synchronized(this){
                var instance = INSTANCE

                if (instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        KontakDb::class.java,
                        "kontak.db"
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}