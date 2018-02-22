package es.carlosdevops.domain.interactor.internetstatus

import android.annotation.SuppressLint
import android.content.Context
import android.net.ConnectivityManager
import android.os.Build
import android.support.annotation.RequiresApi
import es.carlosdevops.domain.interactor.CodeClosure
import es.carlosdevops.domain.interactor.ErrorClosure
import java.lang.ref.WeakReference

class InternetStatusInteractorImpl(context: Context) : InternetStatusInteractor {


    private val weakContext = WeakReference<Context>(context)

    @RequiresApi(Build.VERSION_CODES.M)
    @SuppressLint("MissingPermission")
    override fun execute(success: CodeClosure, error: ErrorClosure) {

        var cm : ConnectivityManager = weakContext.get()!!.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var activeNw = cm.activeNetwork
        success()
    }
}