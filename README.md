# Weather App

A simple and elegant Android weather application that provides real-time weather updates using the OpenWeatherMap API.

## Features

- **Real-time Weather:** Get current temperature, humidity, wind speed, and more.
- **City Search:** Search for weather information by city name.
- **Dynamic Backgrounds:** The app's background and animations change based on the current weather conditions (e.g., Sunny, Rainy, Cloudy, Snowy).
- **Detailed Information:** Displays sunrise/sunset times, sea level pressure, and max/min temperatures.
- **Lottie Animations:** Uses high-quality Lottie animations for a better user experience.

## Screenshots

*(Add screenshots of your app here)*

## Tech Stack

- **Language:** Kotlin
- **UI Framework:** Android XML (View Binding)
- **Networking:** Retrofit with Gson Converter
- **Animations:** Lottie for Android
- **API:** [OpenWeatherMap API](https://openweathermap.org/api)

## Setup and Installation

1.  **Clone the repository:**
    ```bash
    git clone https://github.com/YOUR_USERNAME/WeatherApp.git
    ```
2.  **Open in Android Studio:**
    Open the project folder in Android Studio.
3.  **Add API Key:**
    The app uses an API key from OpenWeatherMap. You can find it in `MainActivity.kt`. For security, it's recommended to move this to a `local.properties` file or use a BuildConfig field in a real-world scenario.
4.  **Build and Run:**
    Sync Gradle and run the app on an emulator or a physical device.

## Project Structure

- `MainActivity.kt`: Handles the main UI logic, API calls, and weather updates.
- `ApiInterface.kt`: Defines the Retrofit interface for API endpoints.
- `WeatherApp.kt`, `Main.kt`, `Sys.kt`, etc.: Data classes for parsing the JSON response from OpenWeatherMap.
- `activity_main.xml`: The layout file for the main screen.

## Dependencies

- `androidx.appcompat:appcompat`
- `com.google.android.material:material`
- `com.squareup.retrofit2:retrofit`
- `com.squareup.retrofit2:converter-gson`
- `com.airbnb.android:lottie`

## License

This project is licensed under the MIT License.

---
Designed by Ankit
