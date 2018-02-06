package es.carlosdevops.repository

import es.carlosdevops.repository.model.ShopEntity

/**
 * Created by carlosledesma on 31/1/18.
 */
interface Repository {

    fun getAllShops(successCompletion: (shops: List<ShopEntity>) -> Unit, errorCompletion: ErrorClosure)

    fun deleteAllShops(successCompletion: () -> Unit, errorCompletion: ErrorClosure)
}