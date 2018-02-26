package es.carlosdevops.madridshops.activity

import android.Manifest.permission.ACCESS_COARSE_LOCATION
import android.Manifest.permission.ACCESS_FINE_LOCATION
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.CameraPosition
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.Marker
import com.google.android.gms.maps.model.MarkerOptions
import es.carlosdevops.domain.interactor.ErrorCompletion
import es.carlosdevops.domain.interactor.SuccessCompletion
import es.carlosdevops.domain.interactor.getallshops.GetAllShopsInteractor
import es.carlosdevops.domain.interactor.getallshops.GetAllShopsInteractorImpl
import es.carlosdevops.domain.model.Shop
import es.carlosdevops.domain.model.Shops
import es.carlosdevops.madridshops.R
import es.carlosdevops.madridshops.adapter.ShopsAdapter
import es.carlosdevops.madridshops.router.Router
import es.carlosdevops.utils.LATITUDE_MADRID
import es.carlosdevops.utils.LONGITUDE_MADRID

import kotlinx.android.synthetic.main.activity_shops.*
import kotlinx.android.synthetic.main.content_main_shops.*

class ShopsActivity : AppCompatActivity(), GoogleMap.OnInfoWindowClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_shops)
        setSupportActionBar(toolbar)


        setupMap()
    }

    private fun setupMap() {

        var getAllShopsInteractor : GetAllShopsInteractor = GetAllShopsInteractorImpl(this)
        getAllShopsInteractor.execute(success = object: SuccessCompletion<Shops> {
            override fun successCompletion(e: Shops) {
                setupList(e)
                initalizeMap(e)

                }
            },error = object: ErrorCompletion {
                override fun errorCompletion(errorMessage: String) {
                    Toast.makeText(baseContext,errorMessage,Toast.LENGTH_SHORT).show()
                }
            })

    }

    private fun setupList(shops: Shops) {

        var shopsAdapter = ShopsAdapter(shops)
        activity_main_list_fragment.layoutManager = LinearLayoutManager(this)
        shopsAdapter.onClickListener = View.OnClickListener {
            val position = activity_main_list_fragment.getChildAdapterPosition(it)
            Router().fromShopActivityToDetailShopActivity(this, shops.get(position))
        }
        activity_main_list_fragment.adapter = shopsAdapter

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
              }
        }
    }

    private fun initalizeMap(shops: Shops) {
        val mapFragment = supportFragmentManager.findFragmentById(R.id.activity_main_map_fragment) as SupportMapFragment
        mapFragment.getMapAsync({
              map = it
            centerMapInPosition(it, LATITUDE_MADRID, LONGITUDE_MADRID)
            it.uiSettings.isRotateGesturesEnabled = false
            showUserPosition(this,it)
            addAllPins(shops)
            map?.setOnInfoWindowClickListener(this)
        })

        map?.setOnMarkerClickListener {

            val shop = it.tag as Shop
            Router().fromShopActivityToDetailShopActivity(this, shop)
            true
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
            addPin(shop.gps_lat,shop.gps_lon,shop.name,shop)
        }
    }

    fun addPin(latitude: Double,longitude: Double,title: String,shop: Shop) {
        map!!.addMarker(MarkerOptions()
                .position(LatLng(latitude,longitude))
                .title(title))
                .tag = shop

    }

    override fun onInfoWindowClick(marker: Marker?) {
        Router().fromShopActivityToDetailShopActivity(this,marker?.tag as Shop)
    }
}
