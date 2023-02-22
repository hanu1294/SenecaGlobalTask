package com.senecaglobal.weatherapp.ui

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import com.senecaglobal.weatherapp.R
import com.senecaglobal.weatherapp.databinding.ActivityWeatherBinding
import com.senecaglobal.weatherapp.util.CustomProgressDialog
import com.senecaglobal.weatherapp.util.LOCATION
import com.senecaglobal.weatherapp.util.Status
import com.senecaglobal.weatherapp.viewModels.WeatherViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class WeatherActivity : AppCompatActivity() {

    private lateinit var binding: ActivityWeatherBinding

    private val viewModel: WeatherViewModel by viewModels()

    private lateinit var progressDialog: CustomProgressDialog
    private var progressDialogLoading = false

    private val degreeSymbol = "\u00B0"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityWeatherBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val actionBar: ActionBar? = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)

        progressDialog = CustomProgressDialog(this)
        progressDialogLoading = false

        if (intent.hasExtra(LOCATION)) {
            //Fetching Weather Information
            intent.getStringExtra(LOCATION)?.let {
                if (it.equals("invalid")) {
                    viewModel.getWeatherInformation(it, "invalidApiKey")
                } else {
                    viewModel.getWeatherInformation(it, getString(R.string.WEATHER_API_KEY))
                }
            }
        }

        //Observing API Response
        addListeners()
    }

    private fun addListeners() {
        viewModel.weatherInformation.observe(this) {
            if (it == null)
                return@observe
            when (it.status) {
                Status.LOADING -> {
                    progressDialog.showProgressDialog()
                    progressDialogLoading = true
                    binding.imgWeather.visibility = View.GONE
                }
                Status.SUCCESS -> {
                    progressDialog.cancelDialog()
                    progressDialogLoading = false
                    binding.imgWeather.visibility = View.VISIBLE
                    it.data?.let {
                        binding.tvLocationName.text = it.name
                        it.weather?.let { weather ->
                            if (weather.isNotEmpty()) {
                                binding.tvWeatherDescription.text =
                                    weather[0]?.description ?: "No data!!"
                            }
                        }
                        it.main?.let {
                            binding.tvWeatherTemparature.text = "${it.temp}$degreeSymbol"
                        }
                        it.coord?.let {
                            var latitude = it.lat
                            var longitude = it.lon
                            binding.tvWeatherLatLongs.text =
                                "L: $latitude$degreeSymbol H: $longitude$degreeSymbol"
                        }
                    }
                }
                Status.ERROR -> {
                    progressDialog.cancelDialog()
                    progressDialogLoading = false
                    binding.imgWeather.visibility = View.GONE
                    binding.tvWeatherDescription.text = it.message
                }
            }
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                finish()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }
}