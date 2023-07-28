package api;

import java.net.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Scanner;

import org.json.JSONObject;

public class Exchange {

	private String apiUrl = "https://api.exchangerate.host/symbols";
	
	
	public ArrayList<String> getSymbols() 
	{
		ArrayList<String> symbolsArray = new ArrayList<String>();
		
		try {
			URI uri = new URI(apiUrl);
			
			HttpURLConnection request = (HttpURLConnection) uri.toURL().openConnection();
			request.setRequestMethod("GET");
			request.connect();
			int statusCode = request.getResponseCode();
			
			if (statusCode == request.HTTP_OK) 
			{
				 StringBuilder informacion = new StringBuilder();
				 Scanner scaner = new Scanner(uri.toURL().openStream());
				 
				 while(scaner.hasNext()) {
					 informacion.append(scaner.nextLine());
				 }
				 
				 scaner.close();
				 
				 System.out.println(informacion);
				 JSONObject jsonObject = new JSONObject(informacion.toString());
				 JSONObject symbolsObject = jsonObject.getJSONObject("symbols");
				 
				 
				 
				 for (String codigo : symbolsObject.keySet()) {
		                JSONObject moneda = symbolsObject.getJSONObject(codigo);
		                String descripcion = moneda.getString("description");
		                symbolsArray.add(codigo + " - " + descripcion);
				 } 
				 
			} else {
				throw new RuntimeException("The status code was: " + statusCode);
			}
			
			request.disconnect();
			
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		Collections.sort(symbolsArray);
		
		return symbolsArray;
	}
	
	
}
