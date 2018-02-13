package es.carlosdevops.madridshops.router

import android.content.Intent
import es.carlosdevops.domain.model.Shop
import es.carlosdevops.madridshops.activity.MainActivity
import es.carlosdevops.madridshops.activity.ShopsActivity

/**
 * Created by carlosledesma on 12/2/18.
 */

class Router {
    fun fromShopActivityToDetailShopActivity(activity: ShopsActivity, shop: Shop) {
        //val intent: Intent = Intent(activity,)
    }

    fun fromMainActivityToShopActivity(activity: MainActivity) {
        val intent = Intent(activity,ShopsActivity::class.java)

        activity.startActivity(intent)
    }

    fun fromMainActivityToActivitiesActivity(activity: MainActivity) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}