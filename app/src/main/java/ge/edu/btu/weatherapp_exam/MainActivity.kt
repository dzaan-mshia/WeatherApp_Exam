package ge.edu.btu.weatherapp_exam

import ge.edu.btu.weatherapp_exam.fragment.HourlyFragment
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope // Import lifecycleScope
import androidx.viewpager2.widget.ViewPager2
import ge.edu.btu.weatherapp_exam.adapter.ViewPagerAdapter
import ge.edu.btu.weatherapp_exam.fragment.TodayFragment
import ge.edu.btu.weatherapp_exam.service.WeatherService
import kotlinx.coroutines.launch // Import launch
import java.sql.Timestamp
import androidx.core.graphics.drawable.toDrawable
import java.time.LocalTime

class MainActivity : AppCompatActivity() {

    private lateinit var todayFragment: TodayFragment
    private lateinit var hourlyFragment: HourlyFragment
    private lateinit var service: WeatherService
    private lateinit var viewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        todayFragment = TodayFragment()
        hourlyFragment = HourlyFragment()
        service = WeatherService(this)
        viewPager = findViewById<ViewPager2>(R.id.viewPager)
        viewPager.adapter = ViewPagerAdapter(this, listOf(todayFragment, hourlyFragment))

        // Call setCity from onCreate, it will now handle its own coroutine scope
        setCity("Tbilisi")
        changeBackgroundColor()
        setListeners()
    }

    private fun setCity(city: String) {
            service.getCityWeather(
                city,
                todayFragment::changeCityStats,
                hourlyFragment::renderForecast)

        this.findViewById<TextView>(R.id.city_name).text = city
    }

    @Suppress("DEPRECATION")
    private fun changeBackgroundColor() {
        if (LocalTime.now().hour in 6..17) {
            findViewById<LinearLayout>(R.id.main_layout).background =
                resources.getColor(R.color.day_background).toDrawable()
        } else {
            findViewById<LinearLayout>(R.id.main_layout).background =
                resources.getColor(R.color.night_background).toDrawable()
        }
    }

    private fun setListeners() {
        findViewById<ImageView>(R.id.georgia).setOnClickListener {
            setCity("Tbilisi")
        }

        findViewById<ImageView>(R.id.uk).setOnClickListener {
            setCity("London")
        }

        findViewById<ImageView>(R.id.jamaica).setOnClickListener {
            setCity("Kingston")
        }

        findViewById<ImageView>(R.id.today).setOnClickListener {
            viewPager.setCurrentItem(0, true)
        }

        findViewById<ImageView>(R.id.hourly).setOnClickListener {
            viewPager.setCurrentItem(1, true)
        }
    }
}