package com.rahmaxiipplg3.psts_fatkhiyaturrahma_xiipplg3
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.rahmaxiipplg3.psts_fatkhiyaturrahma_xiipplg3.adapter.adapterBatik
import com.rahmaxiipplg3.psts_fatkhiyaturrahma_xiipplg3.databinding.ActivityInputDataTokoBatikBinding
import com.rahmaxiipplg3.psts_fatkhiyaturrahma_xiipplg3.db.DBTokoBatik
import com.rahmaxiipplg3.psts_fatkhiyaturrahma_xiipplg3.db.Tb_Pesanan

class input_data_TokoBatik : AppCompatActivity() {

    private lateinit var database : DBTokoBatik
    private lateinit var binding : ActivityInputDataTokoBatikBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityInputDataTokoBatikBinding.inflate(layoutInflater)
        setContentView(binding.root)
        database= DBTokoBatik.getInstance(applicationContext)

        binding.btnSimpanData.setOnClickListener(){
            if (binding.txtKodeBarang.text.isNotEmpty()&&
                    binding.txtNamaBarang.text.isNotEmpty()&&
                    binding.txtJenisBarang.text.isNotEmpty()&&
                    binding.txtNamaBarang.text.isNotEmpty()&&
                    binding.txtHargaBarang.text.isNotEmpty()&&
                    binding.txtKuantiti.text.isNotEmpty()) {

                database.tokobatikDAO().insertData(Tb_Pesanan(
                    binding.txtKodeBarang.text.toString().toInt(),
                    binding.txtJenisBarang.text.toString(),
                    binding.txtNamaBarang.text.toString(),
                    binding.txtHargaBarang.text.toString().toInt(),
                    binding.txtKuantiti.text.toString().toInt()
                ))

                binding.txtKodeBarang.setText("")
                binding.txtJenisBarang.setText("")
                binding.txtNamaBarang.setText("")
                binding.txtHargaBarang.setText("")
                binding.txtKuantiti.setText("")
                startActivity(
                    Intent(this,MainActivity::class.java)
                )

            }else{
                Toast.makeText(applicationContext, "silahkan isi semua datanya terlebih dahulu",
                Toast.LENGTH_SHORT).show()


            }
        }
        binding.imgBack.setOnClickListener{
            startActivity(
                Intent(this,MainActivity::class.java))
        }
    }
}