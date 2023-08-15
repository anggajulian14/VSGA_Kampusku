package com.example.vsga_kampusku.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.vsga_kampusku.R
import com.example.vsga_kampusku.data.entity.Mahasiswa

class MhsAdapter(var list: List<Mahasiswa>) : RecyclerView.Adapter<MhsAdapter.viewHolder>() {

    private lateinit var dialog: Dialog

    fun setDialog(dialog: Dialog){
        this.dialog = dialog
    }

    interface Dialog {
        fun onClick(position: Int)
    }

    inner class viewHolder(view:View) : RecyclerView.ViewHolder(view){
        var nama : TextView
        var nomor : TextView
        var alamat : TextView

        init {
            nama = view.findViewById(R.id.textViewNama)
            nomor = view.findViewById(R.id.textViewNomorMahasiswa)
            alamat = view.findViewById(R.id.textViewAlamat)
            view.setOnClickListener{
                dialog.onClick(layoutPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_mhs, parent,false)
        return viewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.nama.text = list[position].namaLengkap
        holder.nomor.text = list[position].uid
        holder.alamat.text = list[position].alamat
    }
}