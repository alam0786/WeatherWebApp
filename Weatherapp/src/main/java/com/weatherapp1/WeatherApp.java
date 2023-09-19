package com.weatherapp1;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.JSONObject;

@WebServlet("/WeatherServlet")
public class WeatherApp extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		  try {
	            String apiKey = "58eeee5b00fe47799bc150233232308";
	            String cityName = request.getParameter("city");
	            String apiUrl = "https://api.weatherapi.com/v1/current.json?q=" + cityName + "&key=" + apiKey;

	            // Perform HTTP request and get the JSON response
	            URL url = new URL(apiUrl);
	            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
	            connection.setRequestMethod("GET");

	            int responseCode = connection.getResponseCode();

	            if (responseCode == HttpURLConnection.HTTP_OK) {
	                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
	                StringBuilder responseJson = new StringBuilder();
	                String line;
	                while ((line = reader.readLine()) != null) {
	                    responseJson.append(line);
	                }
	                reader.close();

	                // Parse JSON response
	                JSONObject jsonResponse = new JSONObject(responseJson.toString());

	                // Store weather data in request attributes
	                request.setAttribute("locationName", jsonResponse.getJSONObject("location").getString("name"));
	                request.setAttribute("region", jsonResponse.getJSONObject("location").getString("region"));
	                request.setAttribute("country", jsonResponse.getJSONObject("location").getString("country"));
	                request.setAttribute("temperatureCelsius", jsonResponse.getJSONObject("current").getDouble("temp_c"));
	                request.setAttribute("temperatureFahrenheit", jsonResponse.getJSONObject("current").getDouble("temp_f"));
	                request.setAttribute("conditionText", jsonResponse.getJSONObject("current").getJSONObject("condition").getString("text"));
	                request.setAttribute("WindSpeed", jsonResponse.getJSONObject("current").getDouble("wind_kph"));
	                
	                System.out.println(jsonResponse);
	                // Forward the request to the JSP for rendering
	                request.getRequestDispatcher("/index.jsp").forward(request, response);
	            } else {
	                response.getWriter().println("HTTP request failed with response code: " + responseCode);
	            }
	        } catch (java.io.IOException | org.json.JSONException e) {
	            e.printStackTrace();
	        }
		
	}

}
