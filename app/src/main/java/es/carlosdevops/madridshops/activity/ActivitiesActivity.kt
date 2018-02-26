package es.carlosdevops.madridshops.activity

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.View
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import es.carlosdevops.domain.interactor.getallactivities.GetAllActivities
import es.carlosdevops.domain.interactor.getallactivities.GetAllActivitiesImpl
import es.carlosdevops.domain.model.Activities
import es.carlosdevops.domain.interactor.ErrorCompletion
import es.carlosdevops.domain.interactor.SuccessCompletion
import es.carlosdevops.domain.model.Activity
import es.carlosdevops.domain.model.Shop
import es.carlosdevops.domain.model.Shops
import es.carlosdevops.madridshops.R
import es.carlosdevops.madridshops.adapter.ActivitiesAdapter
import es.carlosdevops.madridshops.router.Router
import es.carlosdevops.utils.LATITUDE_MADRID
import es.carlosdevops.utils.LONGITUDE_MADRID
import kotlinx.android.synthetic.main.content_main_activities.*

class ActivitiesActivity : AppCompatActivity(), GoogleMap.OnInfoWindowClickListener {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_activities)

        setupMap()
    }

    private fun setupMap() {

        var getAllActivities : GetAllActivities = GetAllActivitiesImpl(this)

        getAllActivities.execute(success = object :SuccessCompletion<Activities> {
            override fun successCompletion(e: Activities) {
                setupList(e)
                initializeMap(e)
            }
        }, error = object : ErrorCompletion{
            override fun errorCompletion(errorMessage: String) {
                Toast.makeText(baseContext,errorMessage, Toast.LENGTH_SHORT).show()
            }

        })
    }

    private fun setupList(activities: Activities) {
        var activitiesAdapter = ActivitiesAdapter(activities)
        cma_list_fragment.layoutManager = LinearLayoutManager(this)
        activitiesAdapter.onClickListener = View.OnClickListener {
            val position = cma_list_fragment.getChildAdapterPosition(it)
            Router().fromActivitiesActivityToDetailActivitiesActivity(this, activities.get((position)))
        }
        cma_list_fragment.adapter = activitiesAdapter

    }

    private fun initializeMap(activities: Activities) {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.cma_map_fragment) as SupportMapFragment
        mapFragment.getMapAsync {
            map = it
            centerMapInPosition(it, LATITUDE_MADRID, LONGITUDE_MADRID)
            it.uiSettings.isRotateGesturesEnabled = false
            showUserPosition(this,it)
            addAllPins(activities)
            map?.setOnInfoWindowClickListener(this)
        }

    }

    private var map: GoogleMap? = null

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == 10) {
            try {
                map?.isMyLocationEnabled = true
            } catch (e: SecurityException) {
            }
        }
    }

    fun centerMapInPosition(map: GoogleMap, latitude: Double, longitude: Double) {
        val coordinates = LatLng(latitude,longitude)
        val cameraPosition = CameraPosition.builder()
                .target(coordinates)
                .zoom(13f)
                .build()

        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

    }

    fun showUserPosition(context: Context, map: GoogleMap) {
        if(ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION),10)
            return
        }
        map.isMyLocationEnabled = true

    }

    fun addAllPins(activities: Activities) {
        for( i in 0 until activities.count()) {
            val activity = activities.get(i)
            addPin(activity.gps_lat,activity.gps_lon,activity.name,activity)
        }
    }

    fun addPin(latitude: Double,longitude: Double,title: String,activity: Activity) {
        map!!.addMarker(MarkerOptions().position(LatLng(latitude,longitude)).title(title))
                .tag = activity


    }

    override fun onInfoWindowClick(marker: Marker?) {

        Log.d("MAPA",marker?.tag.toString())
        Router().fromActivitiesActivityToDetailActivitiesActivity(this, marker?.tag as Activity)

    }
}
