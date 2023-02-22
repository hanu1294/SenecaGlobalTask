package com.senecaglobal.weatherapp.ui

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.senecaglobal.weatherapp.R
import com.senecaglobal.weatherapp.models.LocationModel
import com.senecaglobal.weatherapp.util.LOCATION

class CitiesAdapter(
    private val context: CitiesListActivity,
    private val mList: ArrayList<LocationModel>
) :
    RecyclerView.Adapter<CitiesAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.cities_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = mList[position]
        holder.tvLocationName.text = model.locationName
        holder.itemView.setOnClickListener {
            val intent = Intent(context, WeatherActivity::class.java)
            intent.putExtra(LOCATION, model.locationKey.toString())
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val tvLocationName: TextView = itemView.findViewById(R.id.tvLocationName)
    }
}