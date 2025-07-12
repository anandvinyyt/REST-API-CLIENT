# REST-API-CLIENT
# REST-API-CLIENT (Weather Fetch Data)

COMPANY : CODTECH IT SOLUTIONS

NAME : VINEET ANAND

INTERN ID : CT12WOJG

DOMAIN : JAVA PROGRAMMING

DURATIONS : 12 WEEKS

MENTOR : NEELA SANTOSH

🌦️ Project Title: Weather Forecast Application using Open-Meteo API

📁 Project Overview:

The WeatherAPIData project is a Java-based console application that fetches real-time weather data for any user-entered city by consuming public REST APIs. It utilizes the Open-Meteo Geocoding and Forecast APIs to obtain the city's geographic coordinates (latitude and longitude) and then fetches the current temperature, humidity, wind speed, and time.

🔧 Technologies Used:

-Java SE (Core Java)

-HTTPURLConnection (for REST API calls)

-JSON.simple library (for parsing JSON)

-Open-Meteo API (for Geocoding and Weather Forecast)

-Scanner (for user input)

🔍 Key Functionalities:

1. User Input & Flow Control
The application runs in a loop, continuously prompting the user to enter a city name.

User can type "No" to exit the application gracefully.

2. Geolocation Lookup (getLocationData)
   
Converts the city name to URL-friendly format (replacing spaces with +).

Calls the Open-Meteo Geocoding API to fetch geographic coordinates.

Parses the API response to extract latitude and longitude of the city.

3. Weather Data Retrieval (displayWeatherData)
   
Uses the retrieved latitude and longitude to call the Open-Meteo Forecast API.

-Retrieves:

-Current Temperature

-Humidity

-Wind Speed

-Timestamp

Displays the structured weather information on the console.

4. REST API Handling
   
fetchApiResponse() handles the API connection setup using HttpURLConnection.

readApiResponse() reads and returns the API JSON response as a string.
