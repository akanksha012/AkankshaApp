/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package akanshaapp.com.geu.akanshaapp.akanshaapp;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * @author KESHAV JHA
 */
public class ApiCal extends AsyncTask<String, Void, String> {


    @Override
    protected String doInBackground(String... params) {
        String response = getTrainInfoResponse(params[0]);

        return response;
    }



    public String getTrainInfoResponse(String... args) {

        //Your API key

        String endpoint = args[0];

        HttpURLConnection request = null;
        BufferedReader rd = null;
        StringBuilder response = null;

        try {
            URL endpointUrl = new URL(endpoint);
            request = (HttpURLConnection) endpointUrl.openConnection();
            request.setRequestMethod("GET");
            request.connect();

            rd = new BufferedReader(new InputStreamReader(request.getInputStream()));
            response = new StringBuilder();
            String line = null;
            while ((line = rd.readLine()) != null) {
                response.append(line + "\n");
            }
        } catch (MalformedURLException e) {
            System.out.println("Exception: " + e.getMessage());
            //e.printStackTrace();
        } catch (ProtocolException e) {
            System.out.println("Exception: " + e.getMessage());
            //e.printStackTrace();
        } catch (IOException e) {
            System.out.println("Exception: " + e.getMessage());
            //e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Exception: " + e.getMessage());
            //e.printStackTrace();
        } finally {
            try {
                request.disconnect();
            } catch (Exception e) {
            }

            if (rd != null) {
                try {
                    rd.close();
                } catch (IOException ex) {
                }
                rd = null;
            }
        }

        return response != null ? response.toString() : "No Response";
    }
}
