package ge.edu.btu.weatherapp_exam.service

import CacheService
import android.util.Log
import android.widget.Toast
import ge.edu.btu.weatherapp_exam.MainActivity
import ge.edu.btu.weatherapp_exam.model.WeatherItem
import ge.edu.btu.weatherapp_exam.model.WeatherResponse
import ge.edu.btu.weatherapp_exam.network.WeatherApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class WeatherService(context: MainActivity) {
    private var context = context
    private var baseUrl: String = "https://api.openweathermap.org/"
    private var apiKey: String = "e8b54eb03d7f8e3f1c8ccf30b0d6be61"
    private var weatherApi: WeatherApi
    private var cache: CacheService<String, List<WeatherItem>>

    init {
        val retrofit: Retrofit = Retrofit.Builder()
            .baseUrl(baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        cache = CacheService(10)
        weatherApi = retrofit.create(WeatherApi::class.java)
    }

     fun getCityWeather(
        city: String,
        todayFragmentFun: (WeatherItem) -> Unit,
        hourlyFragmentFun: (List<WeatherItem>) -> Unit
    ) {
        if (cache.contains(city)) {
            todayFragmentFun(cache.get(city)?.get(0)!!)
            hourlyFragmentFun(cache.get(city)!!)
            return
        }

        var call = weatherApi.fetchCityWeather(city, apiKey)
        call.enqueue(object : Callback<WeatherResponse> {
            override fun onResponse(
                call: Call<WeatherResponse>,
                response: Response<WeatherResponse>
            ) {
                if (response.code() == 200) {
                    todayFragmentFun(response.body()?.list!![0])
                    hourlyFragmentFun(response.body()?.list!!)
                    cache.put(city, response.body()?.list!!)
                } else {
                    Toast.makeText(context, response.message(), Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
                Log.e("Fetch Error", "Couldn't fetch weather!")
                Toast.makeText(context, t.message, Toast.LENGTH_SHORT).show()
            }

        })
    }

}