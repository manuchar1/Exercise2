package com.example.exercise2.ui

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.exercise2.databinding.ItemShopBinding
import com.example.exercise2.model.shops.Shop
import com.example.exercise2.model.shops.Shops
import java.text.SimpleDateFormat
import java.util.*


class ShopsAdapter : RecyclerView.Adapter<ShopsAdapter.ShopsViewHolder>() {

    private val shops = mutableListOf<Shop>()

    inner class ShopsViewHolder(private val binding: ItemShopBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var model: Shop

        @SuppressLint("SimpleDateFormat")
        fun bind() {
            model = shops[adapterPosition]
            itemView.apply {
                Glide.with(this).load(model.backgroundUrl).into(binding.ivBackground)
                Glide.with(this).load(model.logoUrl).into(binding.ivLogo)
                binding.shopName.text = model.name
                /* if (model.isActive!!) {
                     binding.tvDeliveryStatus.text = "No Delivery"
                 }*/
                binding.shopName.text = model.name

            }
            val c: Calendar = Calendar.getInstance()
            val sdf = SimpleDateFormat("yyyy-MM-dd")
            val strDate: String = sdf.format(c.getTime())

            for (i in model.workingHours) {
                if (i.working) {
                    binding.tvDeliveryStatus.text = i.day
                }
            }


            binding.tvDeliveryStatus.text = strDate
        }
        // if true and
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ShopsViewHolder(
        ItemShopBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    override fun onBindViewHolder(holder: ShopsViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = shops.size

    fun setData(shops: MutableList<Shop>) {
        this.shops.addAll(shops)
        notifyDataSetChanged()
    }
}


