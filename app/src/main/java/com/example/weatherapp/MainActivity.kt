package com.example.weatherapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.weatherapp.databinding.ActivityMainBinding
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import androidx.appcompat.widget.SearchView
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        fetchWeatherData("Bhopal")
        searchCity()
    }

    private fun searchCity() {
        val searchView = binding.searchView
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    fetchWeatherData(query)
                }
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return true
            }
        })
    }

    private fun fetchWeatherData(cityName: String) {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://api.openweathermap.org/data/2.5/")
            .build().create(ApiInterface::class.java)

        val response = retrofit.getWeather(cityName, appid = "85d87fc0aa1d3e3ccefb721b82f333bf", units = "metric")
        response.enqueue(object : Callback<WeatherApp> {
            override fun onResponse(
                p0: Call<WeatherApp?>,
                p1: Response<WeatherApp?>
            ) {
                val responseBody = p1.body()
                if (p1.isSuccessful && responseBody != null) {
                    val temperature = responseBody.main.temp.toString()
                    binding.temp.text = "$temperature °C"

                    val humidity = responseBody.main.humidity.toString()
                    binding.Humidity.text = "$humidity %"

                    val windSpeed = responseBody.wind.speed.toString()
                    binding.windSpeed.text = "$windSpeed m/s"

                    val sunRise = responseBody.sys.sunrise.toLong()
                    binding.sunRise.text = convertUnixToTime(sunRise)

                    val sunSet = responseBody.sys.sunset.toLong()
                    binding.sunset.text = convertUnixToTime(sunSet)

                    val seaLevel = responseBody.main.sea_level.toString()
                    binding.sea.text = "$seaLevel hPa"

                    val condition = responseBody.weather.firstOrNull()?.main ?: "unknown"
                    binding.condition.text = condition

                    val maxTemp = responseBody.main.temp_max.toString()
                    binding.maxTemp.text = "Max $maxTemp °C"

                    val minTemp = responseBody.main.temp_min.toString()
                    binding.minTemp.text = "Min $minTemp °C"

                    val weather = responseBody.weather.firstOrNull()?.main ?: "unknown"
                    binding.weather.text = weather

                    binding.day.text = dayName(System.currentTimeMillis())
                    binding.date.text = date()
                    binding.cityName.text = cityName

                    changeWeatherIcon(condition)
                }
            }

            override fun onFailure(
                p0: Call<WeatherApp?>,
                p1: Throwable
            ) {
                Toast.makeText(this@MainActivity, "Failed to fetch weather data", Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun convertUnixToTime(unix: Long): String {
        val sdf = SimpleDateFormat("hh:mm a", Locale.getDefault())
        return sdf.format(Date(unix * 1000L))
    }

    fun date(): String {
        val sdf = SimpleDateFormat("dd MMMM yyyy", Locale.getDefault())
        return sdf.format(Date())
    }

    fun dayName(timestamp: Long): String {
        val sdf = SimpleDateFormat("EEEE", Locale.getDefault())
        return sdf.format(Date(timestamp))
    }

    private fun changeWeatherIcon(conditions: String) {
        when (conditions) {
            "Clear Sky", "Sunny", "Clear" -> {
                binding.root.setBackgroundResource(R.drawable.sunny_background)
                binding.lottieAnimationView.setAnimation(R.raw.sun)
            }
            "Partly Clouds", "Clouds", "Overcast", "Mist", "Foggy", "Haze" -> {
                binding.root.setBackgroundResource(R.drawable.colud_background)
                binding.lottieAnimationView.setAnimation(R.raw.cloud)
            }
            "Light Rain", "Drizzle", "Moderate Rain", "Showers", "Heavy Rain" -> {
                binding.root.setBackgroundResource(R.drawable.rain_background)
                binding.lottieAnimationView.setAnimation(R.raw.rain)
            }
            "Light Snow", "Moderate Snow", "Heavy Snow", "Blizzard" -> {
                binding.root.setBackgroundResource(R.drawable.snow_background)
                binding.lottieAnimationView.setAnimation(R.raw.snow)
            }
            else ->{
                binding.root.setBackgroundResource(R.drawable.snow_background)
                binding.lottieAnimationView.setAnimation(R.raw.snow)
            }
        }
        binding.lottieAnimationView.playAnimation()
    }
}