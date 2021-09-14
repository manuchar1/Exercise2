package com.example.exercise2.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.exercise2.databinding.ItemShopBinding
import com.example.exercise2.model.shops.Shop
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.util.*


class ShopsAdapter : RecyclerView.Adapter<ShopsAdapter.ShopsViewHolder>() {


    private val shop: MutableList<Shop> = mutableListOf()

    inner class ShopsViewHolder(private val binding: ItemShopBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var model: Shop

        @RequiresApi(Build.VERSION_CODES.O)
        @SuppressLint("SimpleDateFormat", "SetTextI18n")
        fun bind() {
            model = shop[adapterPosition]

            itemView.apply {
                Glide.with(this).load(model.backgroundUrl).into(binding.ivBackground)
                Glide.with(this).load(model.logoUrl).into(binding.ivLogo)
                binding.shopName.text = model.name
            }
            binding.shopName.text = model.name


            for (item in model.workingHours.indices) {

                val currentWeekDay = Calendar.MONDAY
                val time = model.workingHours[currentWeekDay]

                val current = LocalDateTime.now()
                val formatter = DateTimeFormatter.ofPattern("EEEE MMM dd yyyy")
                val formatted = current.format(formatter)

                val formatter2 = DateTimeFormatter.ofPattern("EEEE MMM dd yyyy HH:mm:ss")
                val formattedCurrentTime = current.format(formatter2)


                val openHours = "$formatted ${time.from}"
                val closeHours = "$formatted ${time.to}"



                binding.test.text = currentWeekDay.toString()


                fun convertDateInMilliseconds(date: String): Long {
                    val formatter3 =
                        DateTimeFormatter.ofPattern("EEEE MMM dd yyyy HH:mm:ss")
                    val localDate = LocalDateTime.parse(date, formatter3)
                    return localDate.atOffset(ZoneOffset.UTC).toInstant().toEpochMilli()
                }

                val open = convertDateInMilliseconds(openHours)
                val close = convertDateInMilliseconds(closeHours)
                val currentTime = convertDateInMilliseconds(formattedCurrentTime)

                val work = open..close
                if (!work.contains(currentTime)) {

                    binding.apply {
                        ivBackground.setColorFilter(Color.argb(155, 0, 0, 0))
                        ivMoonIcon.isVisible = true
                        tvWorkingHours.isVisible = true
                        btnOrderPlaning.isVisible = true

                        val nextWorkingDay = model.workingHours[currentWeekDay+1]
                        tvWorkingHours.text = "${nextWorkingDay.day}, ${nextWorkingDay.from} - ${nextWorkingDay.to}"
                    }
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ShopsViewHolder(
        ItemShopBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
    )

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ShopsViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount() = shop.size

    fun setData(shop: MutableList<Shop>) {
        this.shop.clear()
        this.shop.addAll(shop)
        notifyDataSetChanged()
    }
}


