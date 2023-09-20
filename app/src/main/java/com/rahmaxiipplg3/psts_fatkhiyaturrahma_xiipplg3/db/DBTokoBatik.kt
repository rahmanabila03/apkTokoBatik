package com.rahmaxiipplg3.psts_fatkhiyaturrahma_xiipplg3.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import kotlinx.coroutines.CoroutineScope

@Database(entities = [Tb_Pesanan::class], version = 1)
abstract class DBTokoBatik : RoomDatabase() {
    abstract fun tokobatikDAO() : TokoBatikDAO

    companion object{
        @Volatile
        private var instance :DBTokoBatik?=null
        private var key = Any()

        fun getInstance (context: Context):DBTokoBatik {
            if (instance == null){
                instance = Room.databaseBuilder(context,DBTokoBatik::class.java,"TOKO_BATIK")
                    .fallbackToDestructiveMigration().allowMainThreadQueries().build()
            }
            return instance!!
        }
    }
}