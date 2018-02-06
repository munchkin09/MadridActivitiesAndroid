package es.carlosdevops.repository

import android.app.Instrumentation
import org.junit.Test



import es.carlosdevops.repository.db.convert
import es.carlosdevops.repository.model.ShopEntity
import es.carlosdevops.repository.model.ShopResponseEntity
import es.carlosdevops.repository.network.json.JsonEntitiesParser
import es.carlosdevops.repository.util.ReadJsonFile
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class JSONParsingTests {

    @Test
    @Throws(Exception::class)
    fun given_valid_string_containing_json_parse_correctly() {
        val shopsJson = ReadJsonFile().loadJSONFromAsset("shops.json")
        assertTrue(false == shopsJson.isEmpty())

        //parsing

        val parser = JsonEntitiesParser()
        val responseEntity = parser.parse<ShopResponseEntity>(shopsJson)
        assertNotNull(responseEntity)
        assertEquals("Cortefiel - Preciados",responseEntity.result[0].name)

    }

    @Test
    @Throws(Exception::class)
    fun given_true_converts_into_1() {
        assertEquals(1, convert(true).toLong())
    }

    @Test
    @Throws(Exception::class)
    fun given_invalid_string_containing_json_with_wrong_latitude_it_parses_correctly() {
        val shopsJson = ReadJsonFile().loadJSONFromAsset("shopsWrongLatitude.json")
        assertTrue(false == shopsJson.isEmpty())

        //parsing

        val parser = JsonEntitiesParser()
        val responseEntity = parser.parse<ShopResponseEntity>(shopsJson)
        assertNotNull(responseEntity)
        assertEquals("Cortefiel - Preciados",responseEntity.result[0].name)

    }




}