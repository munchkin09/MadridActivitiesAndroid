package es.carlosdevops.madridshops


import android.support.multidex.MultiDexApplication
import android.util.Log

class MadridShopsApp: MultiDexApplication() {
    override fun onCreate() {
        super.onCreate()

        Log.d("App","OnCreate()")
    }



    override fun onLowMemory() {
        super.onLowMemory()
    }
}