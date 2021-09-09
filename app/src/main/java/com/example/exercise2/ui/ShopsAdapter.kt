package com.example.exercise2.ui

import android.annotation.SuppressLint
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.graphics.drawable.toDrawable
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.exercise2.R
import com.example.exercise2.databinding.ItemShopBinding
import com.example.exercise2.model.shops.Shop
import java.text.SimpleDateFormat
import java.util.*
import android.R.color
import android.graphics.Color
import androidx.core.view.isVisible
import android.util.Log
import androidx.core.util.toRange
import java.text.ParseException


class ShopsAdapter : RecyclerView.Adapter<ShopsAdapter.ShopsViewHolder>() {

    private val shops = mutableListOf<Shop>()

    inner class ShopsViewHolder(private val binding: ItemShopBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var model: Shop

        @SuppressLint("SimpleDateFormat", "SetTextI18n")
        fun bind() {
            model = shops[adapterPosition]

            itemView.apply {
                Glide.with(this).load(model.backgroundUrl).into(binding.ivBackground)
                Glide.with(this).load(model.logoUrl).into(binding.ivLogo)
                binding.shopName.text = model.name
            }
            binding.shopName.text = model.name

            val c: Calendar = Calendar.getInstance()
            val sdf = SimpleDateFormat("kk:mm:ss")
          //  val currentTime: String = sdf.format(c.time)
            val currentTime = "09:00:00"



            for (time in model.workingHours) {

                val workingHours = time.from..time.to
                val working = time.working

                if (working && !workingHours.contains(currentTime)) {

                    binding.apply {
                        ivBackground.setColorFilter(Color.argb(155, 0, 0, 0))
                        ivMoonIcon.isVisible = true
                        tvWorkingHours.isVisible = true
                        btnOrderPlaning.isVisible = true
                        tvWorkingHours.text = "${time.day}, ${time.from} - ${time.to}"

                    }


                } else {
                    binding.tvCurrentTime.text = "######"

                }
                binding.tvDeliveryStatus.text = workingHours.toString()
               // binding.tvCurrentTime.text = currentTime
            }
        }
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


