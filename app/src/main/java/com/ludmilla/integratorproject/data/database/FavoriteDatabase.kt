package com.ludmilla.integratorproject.data.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ludmilla.integratorproject.data.dao.FavoriteDao
import com.ludmilla.integratorproject.data.model.Favorite

@Database(
    entities = [Favorite::class],
    version = 2,
    exportSchema = false
)

abstract class FavoriteDatabase: RoomDatabase() {
    abstract val favoriteDao : FavoriteDao

    companion object{
        @Volatile
        private var INSTANCE: FavoriteDatabase?= null
        fun getInstance(context: Context):FavoriteDatabase{
            synchronized(this){
                var instance = INSTANCE
                if(instance == null){
                    instance = Room.databaseBuilder(
                        context.applicationContext,
                        FavoriteDatabase::class.java,
                        "favorite_db"
                    ).fallbackToDestructiveMigration()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}