package com.rahmaxiipplg3.psts_fatkhiyaturrahma_xiipplg3.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tb_pesanan")
data class Tb_Pesanan(
    @PrimaryKey
    @ColumnInfo(name = "KODE_PESANAN")val kode_pesanan:Int,
    @ColumnInfo(name = "JENIS") val jenis : String,
    @ColumnInfo(name = "NAMA")val nama :String,
    @ColumnInfo(name = "HARGA")val harga :Int,
    @ColumnInfo(name = "KUANTITI")val kuantiti :Int
)
