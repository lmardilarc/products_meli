package com.co.mercadolibre.productsmeli.presentation.adapter


import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.viewpager.widget.PagerAdapter
import com.bumptech.glide.Glide
import com.co.mercadolibre.productsmeli.data.model.Picture

class ProductPicturesPagerAdapter : PagerAdapter() {

    private lateinit var picturesList: List<Picture>

    fun setPicturesList(picturesList: List<Picture>) {
        this.picturesList = picturesList
    }

    override fun getCount(): Int {
        return picturesList.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val imageView = ImageView(container.context)
        Glide.with(container.context)
            .load(picturesList[position].url)
            .fitCenter()
            .into(imageView)

        container.addView(imageView)
        return imageView
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }
}