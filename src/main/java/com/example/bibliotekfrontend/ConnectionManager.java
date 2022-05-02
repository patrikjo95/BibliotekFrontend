package com.example.bibliotekfrontend;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectionManager {


    public String sendGetRequest(String request) {

        String responseString = "";
        String line;
        try {
            URL url = new URL("http://localhost:3306/" + request);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            System.out.println("status: " + status);

            if(status < 300){
                BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                while((line = reader.readLine()) != null){
                    responseString += line;
                }
                reader.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return responseString;
    }

    public String sendPutRequest(String request) {

        try {
            URL url = new URL("http://localhost:8080/" + request);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("PUT");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            int status = connection.getResponseCode();
            System.out.println("status: " + status);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
