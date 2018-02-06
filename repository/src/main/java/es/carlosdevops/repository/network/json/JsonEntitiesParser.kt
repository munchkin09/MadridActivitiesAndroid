package es.carlosdevops.repository.network.json

import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue

/**
 * Created by carlosledesma on 30/1/18.
 */
class JsonEntitiesParser {
    val mapper = jacksonObjectMapper()

    inline fun <reified T: Any>parse(json: String): T {
        return this.mapper.readValue<T>(json)
    }
}