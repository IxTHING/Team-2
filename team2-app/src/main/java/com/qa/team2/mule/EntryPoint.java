package com.qa.team2.mule;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class EntryPoint {
	
	
	public void sendTraineeToMule(String trainee) throws Exception {
		
		String url = "http://localhost:8082";
		URL obj = new URL(url);
		URLConnection con = (URLConnection) obj.openConnection();

		// add request header
		((HttpURLConnection) con).setRequestMethod("POST");
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
		con.setRequestProperty("Content-Type", "application/json");

		String urlParameters = trainee;
		// Send post request
		con.setDoOutput(true);
		OutputStream os = con.getOutputStream();
		os.write(trainee.getBytes());
		os.flush();
		os.close();
		//DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		
		//This doesnt work as it gives null pointers. Might try to get working.
		//Possible util problem, but its probably me being an idiot.
		//ObjectOutputStream steam = new ObjectOutputStream(con.getOutputStream());
		
		//wr.writeBytes(urlParameters);
		//wr.flush();
		//wr.close();

		int responseCode = ((HttpURLConnection) con).getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);

		BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();

		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();

		// print result
		System.out.println(response.toString());

	}
}
