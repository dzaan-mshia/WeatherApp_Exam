package ge.edu.btu.weatherapp_exam.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import ge.edu.btu.weatherapp_exam.R
import ge.edu.btu.weatherapp_exam.model.WeatherItem

class TodayFragment() : Fragment() {

    var weather: WeatherItem? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_today, container, false)
    }

    override fun onResume() {
        super.onResume()
        weather?.let { changeCityStats(it) }
    }

    fun changeCityStats(weather: WeatherItem) {
        this.weather = weather
        activity?.findViewById<TextView>(R.id.bold_temp_value)?.text =
            weather.main.temperature.toString() + "°"
        activity?.findViewById<TextView>(R.id.temperature_value)?.text =
            weather.main.temperature.toString() + "°"
        activity?.findViewById<TextView>(R.id.feels_like_value)?.text =
            weather.main.feelsLike.toString() + "°"
        activity?.findViewById<TextView>(R.id.humidity_value)?.text =
            weather.main.humidity.toString() + "%"
        activity?.findViewById<TextView>(R.id.pressure_value)?.text =
            weather.main.pressure.toString()
        activity?.findViewById<TextView>(R.id.weather_description_value)?.text =
            weather.weather[0].description.toUpperCase()

        var weatherImage: ImageView? = activity?.findViewById(R.id.weather_image)
        if (weatherImage != null) {
            Glide.with(this)
                .load("https://openweathermap.org/img/wn/${weather.weather[0].icon}@2x.png")
                .into(weatherImage)
        }
    }

}