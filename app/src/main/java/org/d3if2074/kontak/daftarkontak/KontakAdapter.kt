package org.d3if2074.kontak.daftarkontak

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import org.d3if2074.kontak.databinding.ItemKontakBinding
import org.d3if2074.kontak.db.KontakEntity
import org.d3if2074.kontak.model.inputKontak
import java.util.*

class KontakAdapter :
    ListAdapter<KontakEntity, KontakAdapter.ViewHolder>(DIFF_CALLBACK) {

    companion object {
        private val DIFF_CALLBACK =
            object : DiffUtil.ItemCallback<KontakEntity>() {
                override fun areItemsTheSame(
                    oldItem: KontakEntity,
                    newItem: KontakEntity
                ): Boolean {
                    return oldItem.id == newItem.id
                }

                override fun areContentsTheSame(
                    oldItem: KontakEntity,
                    newItem: KontakEntity
                ): Boolean {
                    return oldItem == newItem
                }
            }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemKontakBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    class ViewHolder(private val binding: ItemKontakBinding
    ):RecyclerView.ViewHolder(binding.root){

        fun bind(item : KontakEntity) = with(binding){
                namaTextView.text = item.nama
                nomorTextView.text = item.nomor
        }
    }

}