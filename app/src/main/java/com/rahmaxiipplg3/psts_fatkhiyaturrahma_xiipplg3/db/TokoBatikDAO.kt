package com.rahmaxiipplg3.psts_fatkhiyaturrahma_xiipplg3.db

import androidx.room.*

@Dao
interface TokoBatikDAO {
    @Query("SELECT * FROM tb_pesanan")
    fun getAll():List<Tb_Pesanan>
    @Insert
    fun insertData (vararg tbPesanan: Tb_Pesanan)
    @Delete
    fun deleteData (vararg tbPesanan: Tb_Pesanan)
    @Update
    fun updateData (vararg tbPesanan: Tb_Pesanan)
    @Query("SELECT * FROM tb_pesanan WHERE KODE_PESANAN=:kodePesanan")
    fun getDataKode(kodePesanan: Int):List<Tb_Pesanan>
}