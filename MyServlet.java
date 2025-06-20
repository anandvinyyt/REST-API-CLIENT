package MyPackage;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.Scanner;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

/**
 * Servlet implementation class MyServlet
 */
public class MyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MyServlet() {
		super();

	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String inputData = request.getParameter("userInput");
		// System.out.println(inputData);

		// API setup
		String apiKey = "5fe247506deeb2d6abe72ca069add494";
		// Get the city from the from input
		String city = request.getParameter("city");

		// Create the URL for OpenWeatherMap API request
		String apiUrl = "https://api.openweathermap.org/data/2.5/weather?q=" + city + "&appid=" + apiKey;

		try {

			// API Integration
			URL url = new URL(apiUrl);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");

			// Reading the data from network
			InputStream inputStream = connection.getInputStream();
			InputStreamReader reader = new InputStreamReader(inputStream);

			// Want to store in String
			StringBuilder responseContent = new StringBuilder();

			// take input from the reader, will create scanner object
			Scanner scanner = new Scanner(reader);

			while (scanner.hasNext()) {
				responseContent.append(scanner.nextLine());
			}
			// System.out.println(responseContent);
			scanner.close();

			// TypeCasting & Parsing the data into JSON
			Gson gson = new Gson();
			JsonObject jsonObject = gson.fromJson(responseContent.toString(), JsonObject.class);
			// System.out.println(jsonObject);

			// Date & Time
			long dateTimestamp = jsonObject.get("dt").getAsLong() * 1000;
			String date = new Date(dateTimestamp).toString();

			// Temperature
			double temperatureKelvin = jsonObject.getAsJsonObject("main").get("temp").getAsDouble();
			int temperatureCelcius = (int) (temperatureKelvin - 273.15);

			// Humidity
			int humidity = jsonObject.getAsJsonObject("main").get("humidity").getAsInt();

			// Wind Speed
			double windSpeed = jsonObject.getAsJsonObject("wind").get("speed").getAsDouble();

			// Weather Condition
			String weatherCondition = jsonObject.getAsJsonArray("weather").get(0).getAsJsonObject().get("main")
					.getAsString();

			// Set the data as request attribute (for sending to JSP page)
			request.setAttribute("date", date);
			request.setAttribute("city", city);
			request.setAttribute("temperature", temperatureCelcius);
			request.setAttribute("weatherCondition", weatherCondition);
			request.setAttribute("humidity", humidity);
			request.setAttribute("windSpeed", windSpeed);
			request.setAttribute("weatherData", responseContent.toString());

			connection.disconnect();

		} catch (Exception e) {
			e.printStackTrace();
		}

		// Forward the request to the weather.jsp page for rendering
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}

}
