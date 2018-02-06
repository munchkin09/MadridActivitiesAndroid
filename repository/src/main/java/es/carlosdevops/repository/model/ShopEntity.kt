package es.carlosdevops.repository.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty
import java.util.*

@JsonIgnoreProperties(ignoreUnknown = true)
data class ShopEntity(
        val id: Long,
        var databaseId: Long?,
        val name: String,
        @JsonProperty("description_es") val description: String,
        val gps_lat: String,
        val gps_lon: String,
        val img: String,
        val logo_img: String,
        @JsonProperty("opening_hours_es") val opening_hours: String,
        val address: String) {
}
