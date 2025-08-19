package com.example.e_commerce.Adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.request.RequestOptions
import com.example.e_commerce.Model.SliderModel
import com.example.e_commerce.R

class SliderAdapter(
    private var sliderItems: List<SliderModel>,
    private val viewPager2: ViewPager2
) : RecyclerView.Adapter<SliderAdapter.SliderViewholder>() {
    class SliderViewholder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageView: ImageView = itemView.findViewById(R.id.imageSlide)

        fun setImage(sliderItems: SliderModel, context: Context) {
            val requestOption = RequestOptions().transform(CenterInside())

            Glide.with(context)
                .load(sliderItems.url)
                .apply(requestOption)
                .into(imageView)
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): SliderAdapter.SliderViewholder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.slidebar_item_container, parent, false)
        return SliderViewholder(itemView = view)
    }

    override fun onBindViewHolder(holder: SliderViewholder, position: Int) {
        holder.setImage(sliderItems[position], holder.itemView.context)
        if (position == sliderItems.lastIndex - 1) {
            viewPager2.post(runnable)
        }
    }

    override fun getItemCount() = sliderItems.size

    private val runnable = Runnable {
        sliderItems = sliderItems
        notifyDataSetChanged()
    }
}