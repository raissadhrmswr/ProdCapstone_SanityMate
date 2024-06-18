package com.novia.sanitymate.view.adapter

import android.content.Context
import android.content.res.Resources
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.novia.sanitymate.R

class ViewPagerAdapter(private val context: Context) : PagerAdapter() {

    private val sliderAllImages = intArrayOf(R.drawable.screen1, R.drawable.screen2)
    private val sliderAllTitle = intArrayOf(R.string.screen1, R.string.screen2)
    private val sliderAllDesc = intArrayOf(R.string.screen1desc, R.string.screen2desc)

    override fun getCount(): Int {
        return sliderAllTitle.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as FrameLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = LayoutInflater.from(context)
        val view = layoutInflater.inflate(R.layout.slider_screen, container, false)

        val sliderImage: ImageView = view.findViewById(R.id.sliderImage)
        val sliderTitle: TextView = view.findViewById(R.id.sliderTitle)
        val sliderDesc: TextView = view.findViewById(R.id.sliderDesc)

        sliderImage.setImageResource(sliderAllImages[position])
        sliderTitle.setText(sliderAllTitle[position])
        sliderDesc.setText(sliderAllDesc[position])

        val layoutParams = sliderTitle.layoutParams as LinearLayout.LayoutParams

        layoutParams.topMargin = 0
        layoutParams.bottomMargin = 50.dpToPx()

        sliderTitle.layoutParams = layoutParams

        container.addView(view)
        return view
    }

    fun Int.dpToPx(): Int {
        return (this * Resources.getSystem().displayMetrics.density).toInt()
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as FrameLayout)
    }
}
