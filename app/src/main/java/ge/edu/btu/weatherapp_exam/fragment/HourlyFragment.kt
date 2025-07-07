package ge.edu.btu.weatherapp_exam.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import ge.edu.btu.weatherapp_exam.R
import ge.edu.btu.weatherapp_exam.adapter.RecyclerViewAdapter
import ge.edu.btu.weatherapp_exam.model.WeatherItem

class HourlyFragment() : Fragment() {

    private var weatherList = ArrayList<WeatherItem>()
    private var weatherRV = RecyclerViewAdapter(weatherList, this)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_hourly, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var rvWeather = view.findViewById<RecyclerView>(R.id.recycler_view)
        rvWeather.adapter = weatherRV
        rvWeather.layoutManager = LinearLayoutManager(view.context, RecyclerView.VERTICAL, false)
        var itemDecoration = DividerItemDecoration(rvWeather.context, RecyclerView.VERTICAL)
        itemDecoration.setDrawable(resources.getDrawable(R.drawable.divider))
        rvWeather.addItemDecoration(itemDecoration)
    }

    fun renderForecast(forecast: List<WeatherItem>) {
        weatherList.clear()
        weatherList.addAll(forecast)
        weatherRV.notifyDataSetChanged()
    }

}