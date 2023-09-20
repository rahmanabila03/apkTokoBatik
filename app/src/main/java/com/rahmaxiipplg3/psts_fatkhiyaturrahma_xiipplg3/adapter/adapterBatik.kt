package com.rahmaxiipplg3.psts_fatkhiyaturrahma_xiipplg3.adapter

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.rahmaxiipplg3.psts_fatkhiyaturrahma_xiipplg3.R
import com.rahmaxiipplg3.psts_fatkhiyaturrahma_xiipplg3.db.Tb_Pesanan

class adapterBatik(val list : ArrayList<Tb_Pesanan>,var listener :onAdapterListener): RecyclerView.Adapter<adapterBatik.ViewHolder>() {
    class ViewHolder (view: View): RecyclerView.ViewHolder(view) {
        val idbarang = itemView.findViewById<TextView>(R.id.kodeBarang)
        val jenisbarang =itemView.findViewById<TextView>(R.id.jeniss)
        val namabarang = itemView.findViewById<TextView>(R.id.namaBarang)
        val hargabarang =itemView.findViewById<TextView>(R.id.hargaBarang)
        val kuantiti =itemView.findViewById<TextView>(R.id.kuantiti)
        val delete =itemView.findViewById<ImageView>(R.id.imgDelete)
        val edit =itemView.findViewById<ImageView>(R.id.imgEdit)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.batik_adapter,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.idbarang.text=list[position].kode_pesanan.toString()
        holder.jenisbarang.text=list[position].jenis
        holder.namabarang.text=list[position].nama
        holder.hargabarang.text=list[position].harga.toString()
        holder.kuantiti.text=list[position].kuantiti.toString()

        holder.delete.setOnClickListener{
            listener.onDelete(list[position])
        }
        holder.edit.setOnClickListener{
            listener.onUpdate(list[position])
        }
    }

    fun setData(newList: List<Tb_Pesanan>){
        list.clear()
        list.addAll(newList)
    }
    interface onAdapterListener{
        fun onDelete (tbPesanan: Tb_Pesanan)
        fun onUpdate (tbPesanan: Tb_Pesanan)
    }
    override fun getItemCount(): Int {
        return list.size
    }
}