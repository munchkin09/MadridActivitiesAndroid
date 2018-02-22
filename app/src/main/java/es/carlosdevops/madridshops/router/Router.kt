package es.carlosdevops.madridshops.router

import android.content.Intent
import es.carlosdevops.domain.model.Activity
import es.carlosdevops.domain.model.Shop
import es.carlosdevops.madridshops.activity.*
import es.carlosdevops.utils.INTENT_ACTIVITIES_DETAIL
import es.carlosdevops.utils.INTENT_SHOP_DETAIL

/**
 * Created by carlosledesma on 12/2/18.
 */

class Router {


    fun fromMainActivityToShopActivity(activity: MainActivity) {
        val intent = Intent(activity,ShopsActivity::class.java)

        activity.startActivity(intent)
    }

    fun fromMainActivityToActivitiesActivity(activity: MainActivity) {
        val intent = Intent(activity, ActivitiesActivity::class.java)

        activity.startActivity(intent)
    }

    fun fromShopActivityToDetailShopActivity(activity: ShopsActivity, shop: Shop) {
        val intent = Intent(activity,ShopDetailActivity::class.java)
        intent.putExtra(INTENT_SHOP_DETAIL,shop)

        activity.startActivity(intent)


    }

    fun fromActivitiesActivityToDetailActivitiesActivity(activity: ActivitiesActivity, activityEntity: Activity) {
        val intent = Intent(activity,ActivitiesDetailActivity::class.java)

        intent.putExtra(INTENT_ACTIVITIES_DETAIL, activityEntity)

        activity.startActivity(intent)

    }
}