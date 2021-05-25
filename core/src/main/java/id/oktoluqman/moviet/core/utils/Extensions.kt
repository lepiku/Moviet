package id.oktoluqman.moviet.core.utils

import android.widget.ImageView
import com.bumptech.glide.Glide
import id.oktoluqman.moviet.core.R

object Extensions {
    fun ImageView.loadImage(url: String?) {
        Glide.with(this.context)
            .load(url)
            .placeholder(R.drawable.ic_baseline_refresh_24).centerCrop()
            .error(R.drawable.ic_baseline_broken_image_24).centerCrop()
            .into(this)
    }
}
