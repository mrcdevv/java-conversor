package com.aluraconversor.api;

import java.net.*;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.JSONObject;

import com.aluraconversor.model.Monedas;

public class Exchange {
	
	public String apiConnection(String apiUrl) 
	{
		StringBuilder informacion = new StringBuilder();
		
		try {
			URI uri = new URI(apiUrl);
			
			HttpURLConnection request = (HttpURLConnection) uri.toURL().openConnection();
			request.setRequestMethod("GET");
			request.connect();
			int statusCode = request.getResponseCode();
			
			if (statusCode == request.HTTP_OK) 
			{
				 Scanner scaner = new Scanner(uri.toURL().openStream());
				 
				 while(scaner.hasNext()) {
					 informacion.append(scaner.nextLine());
				 }
				 
				 scaner.close();
				 
			} else {
				throw new RuntimeException("The status code was: " + statusCode);
			}
			
			request.disconnect();
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return informacion.toString();
		
	}
	
	
	public ArrayList<Monedas> getData(String json) 
	{
		ArrayList<Monedas> data = new ArrayList<Monedas>();
		
		
		JSONObject jsonObject = new JSONObject(json);
		JSONObject symbolsObject = jsonObject.getJSONObject("symbols");
		
		
		for (String code : symbolsObject.keySet()) {
			JSONObject currencyObject = symbolsObject.getJSONObject(code);
			String currencyCode = currencyObject.getString("code");
			String currencyDescription = currencyObject.getString("description");
			data.add(new Monedas(currencyCode, currencyDescription));
		}
		
		return data;
	}

	
	public double getExchange(String json)
	{
		JSONObject jsonObject = new JSONObject(json);
		double conversion = jsonObject.getDouble("result");
		return conversion;
	}
}
	

