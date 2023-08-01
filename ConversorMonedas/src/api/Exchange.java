package api;

import java.net.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

import org.json.JSONObject;

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
		
		System.out.println(informacion.toString());
		return informacion.toString();
		
	}
	
	
	public ArrayList<String> getSymbols(String json) 
	{
		ArrayList<String> symbolsArray = new ArrayList<String>();
		
		
		JSONObject jsonObject = new JSONObject(json.toString());
		JSONObject symbolsObject = jsonObject.getJSONObject("symbols");
		
		
		for (String code : symbolsObject.keySet()) {
			JSONObject currencyObject = symbolsObject.getJSONObject(code);
			String currencyCode = currencyObject.getString("code");
			symbolsArray.add(currencyCode);
		}
		
		Collections.sort(symbolsArray);
		
		return symbolsArray;
	}
	
	public ArrayList<String> getDescriptions(String json) {
		
		ArrayList<String> descriptionsArray = new ArrayList<String>();
		
		
		JSONObject jsonObject = new JSONObject(json.toString());
		JSONObject symbolsObject = jsonObject.getJSONObject("symbols");
		
		
		for (String code : symbolsObject.keySet()) {
			JSONObject currencyObject = symbolsObject.getJSONObject(code);
			String currencyCode = currencyObject.getString("code");
			descriptionsArray.add(currencyCode);
		}
		
		Collections.sort(descriptionsArray);
		
		return descriptionsArray;
	}
	
	
}
