package com.example.exercise2.ui

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.exercise2.databinding.ItemShopBinding
import com.example.exercise2.model.shops.Shop
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

class ShopsAdapter : RecyclerView.Adapter<ShopsAdapter.ShopsViewHolder>() {


    private val shop: MutableList<Shop> = mutableListOf()

    inner class ShopsViewHolder(private val binding: ItemShopBinding) :
        RecyclerView.ViewHolder(binding.root) {
        private lateinit var model: Shop

        @SuppressLint("SimpleDateFormat", "SetTextI18n")
        fun bind() {
            model = shop[adapterPosition]

            itemView.apply {
                Glide.with(this).load(model.backgroundUrl).into(binding.ivBackground)
                Glide.with(this).load(model.logoUrl).into(binding.ivLogo)
                binding.shopName.text = model.name
            }
            binding.shopName.text = model.name



            for (time in model.workingHours) {

                val calendar = Calendar.getInstance()
                val simpleDateFormat = SimpleDateFormat("HH:mm:ss")
                val dateTime = simpleDateFormat.format(calendar.time).toString()
                binding.tvDeliveryStatus.text = dateTime


                try {

                    /*   fun getDayOfWeek(date:Date) : Int{
                           val c = Calendar.getInstance()
                           c.time = date
                           return c[Calendar.DAY_OF_WEEK]

                       }*/
                    val openTime = time.from     //"20:11:13"
                    val time1 = SimpleDateFormat("HH:mm:ss").parse(openTime)
                    val calendar1 = Calendar.getInstance()
                    calendar1.time = time1

                    calendar1.add(Calendar.DAY_OF_WEEK, 1)
                    val string2 = time.to    //"14:49:00"
                    val time2 = SimpleDateFormat("HH:mm:ss").parse(string2)
                    val calendar2 = Calendar.getInstance()

                    // current date
                    calendar2.time = time2
                   // calendar2.add(Calendar.DAY_OF_WEEK, 0)
                    // val dd = calendar2.get(Calendar.DAY_OF_WEEK)
                    val someRandomTime = dateTime
                    val d = SimpleDateFormat("HH:mm:ss").parse(someRandomTime)

                    val calendar3 = Calendar.getInstance()
                    calendar3.time = d
                    calendar3.add(Calendar.DAY_OF_WEEK, 1)
                    val x = calendar3.time
                    if (x.after(calendar1.time) && x.before(calendar2.time)) {
                        //checkes whether the current time is between 14:49:00 and 20:11:13.
                        // println(true)

                      //  binding.tvDeliveryStatus.text = "######"

                        binding.apply {
                            ivBackground.setColorFilter(Color.argb(155, 0, 0, 0))
                            ivMoonIcon.isVisible = true
                            tvWorkingHours.isVisible = true
                            btnOrderPlaning.isVisible = true
                            // tvWorkingHours.text = "${time.day}, $openTime - $closeTime"
                        }
                    }
                } catch (e: ParseException) {
                    e.printStackTrace()
                }


                /*try {
                    val startingTime = time.from
                    val finishingTime = time.to
                    val formatShort = SimpleDateFormat("HH:MM")
                    val openTime = formatShort.format(formatShort.parse(startingTime))
                    val closeTime = formatShort.format(formatShort.parse(finishingTime))
                    // val workingHours = openTime..closeTime
                    val isWorking = time.working

                    fun currentTimeBetweenGivenString(s1: String, s2: String): Boolean {
                        val simpleDateFormat = SimpleDateFormat("HH:MM")
                        val date1: Date = simpleDateFormat.parse(s1)
                        val date2: Date = simpleDateFormat.parse(s2)
                        val d = Date()
                        val s3 = simpleDateFormat.format(d)
                        val date3 = simpleDateFormat.parse(s3)

                        return date3 >= date1 && date3 <= date2
                    }
                    if (isWorking && currentTimeBetweenGivenString(openTime, closeTime)) {

                     //   binding.tvCurrentTime.text = "######"

                        binding.apply {
                            ivBackground.setColorFilter(Color.argb(155, 0, 0, 0))
                            ivMoonIcon.isVisible = true
                            tvWorkingHours.isVisible = true
                            btnOrderPlaning.isVisible = true
                            tvWorkingHours.text = "${time.day}, $openTime - $closeTime"
                        }
                    }
                    // binding.tvDeliveryStatus.text = workingHours.toString()
                } catch (e: ParseException) {
                    e.printStackTrace()
                }*/
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

    override fun getItemCount() = shop.size

    fun setData(shop: MutableList<Shop>) {
        this.shop.clear()
        this.shop.addAll(shop)
        notifyDataSetChanged()
    }
}


