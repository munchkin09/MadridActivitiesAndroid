package es.carlosdevops.madridshops.activity

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import es.carlosdevops.domain.interactor.ErrorCompletion
import es.carlosdevops.domain.interactor.SuccessCompletion
import es.carlosdevops.domain.interactor.getallshops.GetAllShopsInteractor
import es.carlosdevops.domain.interactor.getallshops.GetAllShopsInteractorImpl
import es.carlosdevops.domain.model.Shops
import es.carlosdevops.madridshops.R
import es.carlosdevops.madridshops.fragment.ListFragment

import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var listFragment : ListFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)

        Log.e("Activity","OnCreate()")

        setupMap()
        setupList()

        //val fm : FragmentManager = supportFragmentManager.getFragment()
    }

    private fun setupList() {
        listFragment = supportFragmentManager.findFragmentById(R.id.activity_main_list_fragment) as ListFragment
    }

    private fun setupMap() {

        var getAllShopsInteractor : GetAllShopsInteractor = GetAllShopsInteractorImpl(this)
        getAllShopsInteractor.execute(success = object: SuccessCompletion<Shops> {
            override fun successCompletion(e: Shops) {
                Log.d("App", "Tiendas " + e.count())
                initalizeMap(e)

                }
            },error = object: ErrorCompletion {
                override fun errorCompletion(errorMessage: String) {
                    Toast.makeText(baseContext,errorMessage,Toast.LENGTH_SHORT).show()
                }
            })

    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        return when (item.itemId) {
            R.id.action_settings -> true
            else -> super.onOptionsItemSelected(item)
        }
    }

    private var map: GoogleMap? = null

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)

        if(requestCode == 10) {
            try {
                map?.isMyLocationEnabled = true
            } catch (e: SecurityException) {
                Log.d("App",e.toString())
            }
        }
    }

    private fun initalizeMap(shops: Shops) {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.activity_main_map_fragment) as SupportMapFragment
        mapFragment.getMapAsync({
            Log.d("MapFragment", "SUCCESS")
            map = it
            centerMapInPosition(it,40.416775,-3.703790)
            it.uiSettings.isRotateGesturesEnabled = false
            showUserPosition(this,it)
            addAllPins(shops)
        })
    }

    fun centerMapInPosition(map: GoogleMap, latitude: Double, longitude: Double) {
        val coordinates = LatLng(latitude,longitude)
        val cameraPosition = CameraPosition.builder()
                .target(coordinates)
                .zoom(13f)
                .build()

        map.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition))

    }

    fun showUserPosition(context: Context,map: GoogleMap) {
        if(ActivityCompat.checkSelfPermission(context,ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(context,ACCESS_COARSE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, arrayOf(ACCESS_FINE_LOCATION, ACCESS_COARSE_LOCATION),10)
            return
        }
        map.isMyLocationEnabled = true

    }

    fun addAllPins(shops: Shops) {
        for( i in 0 until shops.count()) {
            val shop = shops.get(i)
            addPin(shop.gps_lat,shop.gps_lon,shop.name)
        }
    }

    fun addPin(latitude: Double,longitude: Double,title: String) {
        map?.addMarker(MarkerOptions().position(LatLng(latitude,longitude)).title(title))
    }
}
