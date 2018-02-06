package es.carlosdevops.repository

import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import es.carlosdevops.repository.network.GetJsonManager
import es.carlosdevops.repository.network.GetJsonManagerVolleyImpl
import org.junit.Assert.*
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by carlosledesma on 30/1/18.
 */


@RunWith(AndroidJUnit4::class)
class VolleyTest {
  val appContext = InstrumentationRegistry.getTargetContext()


    @Test
    @Throws(Exception::class)
    fun given_valid_url_we_get_non_null_json_as_string() {
        val url = "http://madrid-shops.com/json_new/getShops.php"

        val jsonManager : GetJsonManager = GetJsonManagerVolleyImpl(appContext)

        jsonManager.execute(url,
            object : SuccessCompletion<String> {
                override fun successCompletion(e: String) {
                    assertTrue(e.isNotEmpty())
                }
            },
            object : ErrorCompletion {
                override fun errorCompletion(errorMessage: String) {
                    assertTrue(false)
                }
        })
    }
}