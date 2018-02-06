package es.carlosdevops.repository

import android.app.Instrumentation
import org.junit.Test



import es.carlosdevops.repository.db.convert
import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see [Testing documentation](http://d.android.com/tools/testing)
 */
class DBHelperTests {
    @Test
    @Throws(Exception::class)
    fun given_false_converts_int_0() {
        assertEquals(0, convert(false).toLong())
    }

    @Test
    @Throws(Exception::class)
    fun given_true_converts_into_1() {
        assertEquals(1, convert(true).toLong())
    }


}