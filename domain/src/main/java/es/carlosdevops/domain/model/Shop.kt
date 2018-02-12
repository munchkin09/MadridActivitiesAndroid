package es.carlosdevops.domain.model

import java.io.Serializable


data class Shop(val id: Long,
                val name:String,
                val address: String,
                val description: String,
                val gps_lat: Double,
                val gps_lon: Double,
                val img: String,
                val logo_img: String,
                val opening_hours: String) : Serializable

class Shops(var shops: MutableList<Shop>): Aggregate<Shop> {
    override fun count(): Int {
        return shops.size
    }

    override fun all(): List<Shop> {
        return shops
    }

    override fun get(position: Int): Shop {
        return shops.get(position)
    }

    override fun add(element: Shop) {
        shops.add(element)
    }

    override fun delete(position: Int) {
        shops.removeAt(position)
    }

    override fun delete(element: Shop) {
        shops.remove(element)
    }

}