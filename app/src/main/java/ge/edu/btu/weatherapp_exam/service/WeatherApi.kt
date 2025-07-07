package ge.edu.btu.weatherapp_exam.network

import ge.edu.btu.weatherapp_exam.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("data/2.5/forecast?units=metric")
    fun fetchCityWeather(
        @Query("q") cityName: String,
        @Query("appid") apiKey: String
    ): Call<WeatherResponse>

}