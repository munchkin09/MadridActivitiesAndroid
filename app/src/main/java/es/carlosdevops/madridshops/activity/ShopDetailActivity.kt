package es.carlosdevops.madridshops.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.squareup.picasso.Picasso
import es.carlosdevops.domain.model.Shop
import es.carlosdevops.madridshops.R
import es.carlosdevops.utils.GOOGLE_MAP_URL
import es.carlosdevops.utils.INTENT_SHOP_DETAIL
import kotlinx.android.synthetic.main.activity_shop_detail.*

class ShopDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_shop_detail)

        val shop = intent.getSerializableExtra(INTENT_SHOP_DETAIL) as Shop

        setupUI(shop)


    }

    private fun setupUI(shop: Shop) {

        asd_txt_description.text = shop.img
        asd_txt_opening.text = shop.logo_img

        Picasso.with(this).load(shop.address).into(asd_img_shop)
        asd_img_shop.scaleType = ImageView.ScaleType.FIT_START


        val googleMapUrl = GOOGLE_MAP_URL + "${shop.gps_lat},${shop.gps_lon}"

        Picasso.with(this).load(googleMapUrl).into(asd_img_map)
    }
}
