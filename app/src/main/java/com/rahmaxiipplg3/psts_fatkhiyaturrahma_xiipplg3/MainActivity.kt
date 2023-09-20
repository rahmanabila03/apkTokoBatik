package com.rahmaxiipplg3.psts_fatkhiyaturrahma_xiipplg3

import android.content.DialogInterface
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager.VERTICAL
import com.rahmaxiipplg3.psts_fatkhiyaturrahma_xiipplg3.adapter.adapterBatik
import com.rahmaxiipplg3.psts_fatkhiyaturrahma_xiipplg3.databinding.ActivityMainBinding
import com.rahmaxiipplg3.psts_fatkhiyaturrahma_xiipplg3.db.DBTokoBatik
import com.rahmaxiipplg3.psts_fatkhiyaturrahma_xiipplg3.db.Tb_Pesanan
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainActivity : AppCompatActivity() {

  private lateinit var binding: ActivityMainBinding
  private lateinit var adapter : adapterBatik
  private val db by lazy {DBTokoBatik.getInstance(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter= adapterBatik(arrayListOf(),
        object : adapterBatik.onAdapterListener{
            override fun onDelete(tbPesanan: Tb_Pesanan) {
                hapusData(tbPesanan)
            }

            override fun onUpdate(tbPesanan: Tb_Pesanan) {
                editData(tbPesanan)
            }

        })

        binding.listData.adapter=adapter
        binding.listData.layoutManager =LinearLayoutManager(applicationContext,VERTICAL,false)
        binding.listData.addItemDecoration(DividerItemDecoration(applicationContext, VERTICAL))

        binding.floatingTambahData.setOnClickListener{
            startActivity(
                Intent(this,input_data_TokoBatik::class.java)
            )

        }
        getData()
        }
    private fun hapusData (tbPesanan: Tb_Pesanan){
        val dialog = AlertDialog.Builder(this)
        dialog.apply {
            setTitle("Konfirmasi hapus data")
            setMessage("apakah anda yakin akan menghapus data ini ${tbPesanan.nama}?")
            setNegativeButton("batal") { dilogInterface: DialogInterface, i: Int ->
                dilogInterface.dismiss()
            }
            setPositiveButton("hapus") { dialogInterface: DialogInterface, i: Int ->
                dialogInterface.dismiss()

                CoroutineScope(Dispatchers.IO).launch {
                    db.tokobatikDAO().deleteData(tbPesanan)
                    finish()
                    startActivity(intent)
                }
            }
            dialog.show()
        }
    }

    private fun editData(tbPesanan: Tb_Pesanan){
        startActivity(Intent(this,Edit_data::class.java)
            .putExtra("kode pesanan",tbPesanan.kode_pesanan.toString()))
    }
    override fun onResume() {
        super.onResume()
        getData()
    }
    fun getData(){
        binding.listData.layoutManager=LinearLayoutManager(applicationContext)
        CoroutineScope(Dispatchers.IO).launch {
            val data = db.tokobatikDAO().getAll()
            adapter.setData(data)
            withContext(Dispatchers.Main){
                adapter.notifyDataSetChanged()
            }
        }
        binding.listData.adapter=adapter

    }
}

