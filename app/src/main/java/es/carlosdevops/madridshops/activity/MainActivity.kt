package es.carlosdevops.madridshops.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.squareup.picasso.Picasso
import es.carlosdevops.madridshops.R
import es.carlosdevops.madridshops.router.Router
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        am_btn_shop.text = getString(R.string.am_txt_shop)
        am_btn_activities.text = getString(R.string.am_txt_activities)
        Picasso.with(this).load(getString(R.string.url_am_img_background)).into(am_img_background)

        am_btn_shop.setOnClickListener {
            Router().fromMainActivityToShopActivity(this)
        }

        am_btn_activities.setOnClickListener {
            Router().fromMainActivityToActivitiesActivity(this)
        }
    }
}
