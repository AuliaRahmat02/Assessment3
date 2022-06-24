package org.d3if2074.kontak.kontakpenting

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import org.d3if2074.kontak.R
import org.d3if2074.kontak.databinding.ListKontakpentingBinding
import org.d3if2074.kontak.model.KontakPenting
import org.d3if2074.kontak.network.KontakApi

class PentingAdapter : RecyclerView.Adapter<PentingAdapter.ViewHolder>() {

    private val data = mutableListOf<KontakPenting>()

    fun updateData(newData: List<KontakPenting>) {
        data.clear()
        data.addAll(newData)
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: ListKontakpentingBinding
    ) : RecyclerView.ViewHolder(binding.root){
        fun bind(kontakPenting: KontakPenting)= with(binding){
            namaInput.text =kontakPenting.nama
            nomorInput.text = kontakPenting.nomor
            Glide.with(imageView.context)
                .load(KontakApi.getKontakUrl(kontakPenting.image))
                .error(R.drawable.ic_baseline_broken_image_24)
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding =  ListKontakpentingBinding.inflate(inflater, parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(data[position])
    }

    override fun getItemCount(): Int {
        return data.size
    }

}