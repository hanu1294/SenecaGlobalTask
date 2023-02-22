package com.senecaglobal.weatherapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.senecaglobal.weatherapp.databinding.ActivityCitiesListBinding
import com.senecaglobal.weatherapp.models.LocationModel

class CitiesListActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCitiesListBinding

    private val locationsList = arrayListOf(
        LocationModel("Hyderabad", "hyderabad"),
        LocationModel("Mumbai", "mumbai"),
        LocationModel("Kolkata", "kolkata"),
        LocationModel("Chennai", "chennai"),
        LocationModel("No Data", "hyderabad1234"),
        LocationModel("Invalid API", "invalid")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCitiesListBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUI()
    }

    private fun setUI() {
        binding.rvCities.layoutManager = LinearLayoutManager(this)
        val adapter = CitiesAdapter(this, locationsList)
        binding.rvCities.adapter = adapter
    }
}