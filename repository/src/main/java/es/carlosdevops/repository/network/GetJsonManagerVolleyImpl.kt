package es.carlosdevops.repository.network

import android.content.Context
import android.util.Log
import com.android.volley.RequestQueue
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import es.carlosdevops.repository.ErrorCompletion
import es.carlosdevops.repository.SuccessCompletion
import java.lang.ref.WeakReference

internal class GetJsonManagerVolleyImpl(context: Context): GetJsonManager {

    var rq : RequestQueue? = null
    var weakContext : WeakReference<Context> = WeakReference(context)

    override fun execute(url: String, success: SuccessCompletion<String>, error: ErrorCompletion) {

        //CreateRequest
        val request = StringRequest(
                url,
                Response.Listener {
                    Log.d("JSON",it)
                    success.successCompletion(it)
                },
                Response.ErrorListener {
                    error.errorCompletion(it.localizedMessage)

                }
        )
        //AddRequestToQueue
        requestQueue().add(request)
    }

    fun requestQueue() : RequestQueue {
        if (rq == null) {
            rq = Volley.newRequestQueue(weakContext.get())
        }

        return rq!!
    }

}