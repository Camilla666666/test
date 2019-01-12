import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Main2 {
    static Scanner scan = new Scanner(System.in);
    static URL url ;
    static HttpURLConnection urlConnection ;
    static InputStream inputStream;
    public static void main(String[] args) {

        StringBuilder stringBuilder = new StringBuilder();
        getUrl();
        try {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = "";
            while ((line = bufferedReader.readLine()) != null) {
                stringBuilder.append(line);
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
        }
        try {
            JSONObject jsonObject = new JSONObject(stringBuilder.toString());
            String city = jsonObject.getString("name");
            JSONArray weather = jsonObject.getJSONArray("weather");
            String description = weather.getJSONObject(0).getString("description");
            int temp = jsonObject.getJSONObject("main").getInt("temp");
            int pressure = jsonObject.getJSONObject("main").getInt("pressure");
            int humidity = jsonObject.getJSONObject("main").getInt("humidity");
            int visibility = jsonObject.getInt("visibility");




            System.out.println("Now in " + city + "\nweather:" + description + "\ntemperature:" + temp + "\npressure:" + pressure + "\nhumidity:" + humidity + "\nvisibility:" + visibility);
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public static void getUrl(){
        try {
            url = new URL("http://api.openweathermap.org/data/2.5/weather?q=" + scan.nextLine() + "&units=metric&APPID=6bab4d6713adbf3a428b1f2a7454395d");
            urlConnection = (HttpURLConnection) url.openConnection();
            inputStream = urlConnection.getInputStream();
        } catch (IOException e) {

            System.out.println("City not found");
            getUrl();
        }
    }
}

