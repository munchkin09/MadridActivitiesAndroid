package es.carlosdevops.madridshops.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import com.squareup.picasso.Picasso
import es.carlosdevops.domain.model.Activity
import es.carlosdevops.madridshops.R
import es.carlosdevops.utils.GOOGLE_MAP_URL
import es.carlosdevops.utils.INTENT_ACTIVITIES_DETAIL
import kotlinx.android.synthetic.main.activity_activities_detail.*

class ActivitiesDetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_activities_detail)

        val activity = intent.getSerializableExtra(INTENT_ACTIVITIES_DETAIL) as Activity
        setupUI(activity)

    }

    private fun setupUI(activity: Activity) {
        aad_txt_description.text = activity.img
        aad_txt_opening.text = activity.logo_img

        Picasso.with(this).load(activity.address).into(aad_img_shop)
        aad_img_shop.scaleType = ImageView.ScaleType.FIT_START


        val googleMapUrl = GOOGLE_MAP_URL + "${activity.gps_lat},${activity.gps_lon}"

        Picasso.with(this).load(googleMapUrl).into(aad_img_map)

    }
}
