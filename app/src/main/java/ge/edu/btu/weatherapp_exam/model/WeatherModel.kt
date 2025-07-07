package ge.edu.btu.weatherapp_exam.model

import com.google.gson.annotations.SerializedName

data class Main(
    @SerializedName("temp")
    val temperature: Double,
    @SerializedName("feels_like")
    val feelsLike: Double,
    val pressure: Double,
    val humidity: Double
)

data class WeatherDescription(
    val description: String,
    val icon: String
)

data class WeatherItem(
    @SerializedName("dt")
    val dateTime: Long,
    val main: Main,
    val weather: List<WeatherDescription>
)

data class WeatherResponse(
    val list: List<WeatherItem>
)