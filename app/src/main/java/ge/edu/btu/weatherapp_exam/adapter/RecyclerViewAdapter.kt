package ge.edu.btu.weatherapp_exam.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import ge.edu.btu.weatherapp_exam.R
import ge.edu.btu.weatherapp_exam.model.WeatherItem
import java.sql.Timestamp
import java.text.DateFormatSymbols

class RecyclerViewAdapter(items: List<WeatherItem>, parentActivity: Fragment) :
    RecyclerView.Adapter<RecyclerViewAdapter.WeatherViewHolder>() {
    var parentActivity = parentActivity
    var items = items

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.weather_row, parent, false)
        )
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        var weatherData = items[position]
        holder.date.text = parseDate(weatherData.dateTime)
        Glide.with(parentActivity)
            .load("https://openweathermap.org/img/wn/${weatherData.weather[0].icon}@2x.png")
            .into(holder.icon)
        holder.temperature.text = weatherData.main.temperature.toString() + "°"
        holder.description.text = weatherData.weather[0].description
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class WeatherViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var date: TextView = itemView.findViewById(R.id.item_date)
        var icon: ImageView = itemView.findViewById(R.id.item_icon)
        var temperature: TextView = itemView.findViewById(R.id.item_temperature)
        var description: TextView = itemView.findViewById(R.id.item_description)
    }

    private fun parseDate(timestamp: Long): String {
        var result: String

        var dateTime = Timestamp(timestamp * 1000)

        /*
            TIME
         */
        var period: String = if (dateTime.hours in 1..12)
            "AM"
        else
            "PM"

        var hour = dateTime.hours % 12

        result = if (hour < 10)
            "0$hour"
        else
            hour.toString()

        result += " $period"

        /*
            DATE
         */

        var date = if (dateTime.date < 10)
            "0${dateTime.date}"
        else
            dateTime.date

        var month = DateFormatSymbols.getInstance().months[dateTime.month]

        result += " $date $month"

        return result
    }

}