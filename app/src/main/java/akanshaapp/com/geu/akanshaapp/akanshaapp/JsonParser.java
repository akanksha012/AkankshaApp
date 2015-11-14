package akanshaapp.com.geu.akanshaapp.akanshaapp;

import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by GB on 11/14/2015.
 */
public class JsonParser {


    public static void makeHttpRequest(String urls,String...values)
    {
        InputStream inputStream = null;
    HttpURLConnection urlConnection = null;
    Integer result = 0;
    try {
                /* forming th java.net.URL object */
        URL url = new URL(urls);
        urlConnection = (HttpURLConnection) url.openConnection();

                 /* optional request header */
        urlConnection.setRequestProperty("Content-Type", "application/json");

                /* optional request header */
        urlConnection.setRequestProperty("Accept", "application/json");

                /* for Get request */
        urlConnection.setRequestMethod("GET");
        int statusCode = urlConnection.getResponseCode();

                /* 200 represents HTTP OK */
        if (statusCode == 200) {
            inputStream = new BufferedInputStream(urlConnection.getInputStream());
            String response = convertInputStreamToString(inputStream);

            result = 1; // Successful
        } else {
            result = 0; //"Failed to fetch data!";
        }
    } catch (Exception e) {
        Log.d("error", e.getLocalizedMessage());
    }

}

    private static String convertInputStreamToString(InputStream inputStream) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        String line = "";
        String result = "";
        while ((line = bufferedReader.readLine()) != null) {
            result += line;
        }

            /* Close Stream */
        if (null != inputStream) {
            inputStream.close();
        }
        return result;
    }


}
