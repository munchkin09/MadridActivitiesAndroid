package es.carlosdevops.repository.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties

/**
 * Created by carlosledesma on 31/1/18.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
internal class ShopResponseEntity(
        val result: List<ShopEntity>
)