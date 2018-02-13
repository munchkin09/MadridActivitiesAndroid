package es.carlosdevops.repository.cache

import es.carlosdevops.repository.ErrorClosure
import es.carlosdevops.repository.ErrorCompletion
import es.carlosdevops.repository.SuccessCompletion
import es.carlosdevops.repository.model.ActivityEntity
import es.carlosdevops.repository.model.ShopEntity

/**
 * Created by carlosledesma on 31/1/18.
 */
interface Cache {

    fun getAllShops(successCompletion: (shops: List<ShopEntity>) -> Unit, errorCompletion: ErrorClosure)
    fun storeAllShops(shops: List<ShopEntity>,successCompletion: () -> Unit, errorCompletion: ErrorClosure)
    fun deleteAllShops(successCompletion: () -> Unit, errorCompletion: ErrorClosure)
    fun getAllActivities(successCompletion: (activities: List<ActivityEntity>) -> Unit, errorCompletion: ErrorClosure)
    fun storeAllActivities(activities: List<ActivityEntity>,successCompletion: () -> Unit, errorCompletion: ErrorClosure)
}