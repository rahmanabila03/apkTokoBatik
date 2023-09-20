package com.rahmaxiipplg3.psts_fatkhiyaturrahma_xiipplg3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.rahmaxiipplg3.psts_fatkhiyaturrahma_xiipplg3.databinding.ActivityEditDataBinding
import com.rahmaxiipplg3.psts_fatkhiyaturrahma_xiipplg3.db.DBTokoBatik
import com.rahmaxiipplg3.psts_fatkhiyaturrahma_xiipplg3.db.Tb_Pesanan
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Edit_data : AppCompatActivity() {

    private lateinit var binding : ActivityEditDataBinding
    private val db by lazy{DBTokoBatik.getInstance(this)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditDataBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val kode = intent.getStringExtra("kode pesanan").toString().toInt()
        val data = db.tokobatikDAO().getDataKode(kode)

        binding.txtKodeBarang.setText(data[0].kode_pesanan.toString())
        binding.txtJenisBarang.setText(data[0].jenis)
        binding.txtNamaBarang.setText(data[0].nama)
        binding.txtHargaBarang.setText(data[0].harga.toString())
        binding.txtKuantiti.setText(data[0].kuantiti.toString())

        binding.txtSelesai.setOnClickListener{
            if (binding.txtKodeBarang.text.isNotEmpty()&&
                    binding.txtJenisBarang.text.isNotEmpty()&&
                    binding.txtNamaBarang.text.isNotEmpty()&&
                    binding.txtHargaBarang.text.isNotEmpty()&&
                    binding.txtKuantiti.text.isNotEmpty()){

                db.tokobatikDAO().updateData(Tb_Pesanan(
                    kode,
                    binding.txtJenisBarang.text.toString(),
                    binding.txtNamaBarang.text.toString(),
                    binding.txtHargaBarang.text.toString().toInt(),
                    binding.txtKuantiti.text.toString().toInt()

                ))
                Toast.makeText(applicationContext,"Data telah berhasil berubah",
                    Toast.LENGTH_SHORT).show()
                startActivity(
                    Intent(this,MainActivity::class.java)
                )
                onBackPressed()
            }else{
                Toast.makeText(applicationContext,"Silahkan ubah data terlebih dahulu",
                    Toast.LENGTH_SHORT).show()
            }
        }
    }
}