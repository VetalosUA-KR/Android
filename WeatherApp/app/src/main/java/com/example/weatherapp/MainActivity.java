package com.example.weatherapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private final String weatherURL = "http://api.openweathermap.org/data/2.5/weather?q=%s&appid=e58dfe2ffb5a5fdbd478a00f564c2b8d&lang=ru&units=metric";

    private EditText editTextCity;
    private TextView textViewWeather;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextCity = findViewById(R.id.editTextCity);
        textViewWeather = findViewById(R.id.textViewWeather);
    }

    public void onClickShowWeather(View view) {
        String city = editTextCity.getText().toString().trim();
        if(!city.isEmpty()){
            DownloadWeatherTask task = new DownloadWeatherTask();
            String url = String.format(weatherURL, city);
            task.execute(url);
        }
    }

    private class DownloadWeatherTask extends AsyncTask <String, Void, String> {

        @Override
        protected String doInBackground(String... strings) {
            URL url = null;
            HttpURLConnection urlConnection = null;
            StringBuilder result = new StringBuilder();
            try {
                url = new URL(strings[0]);
                urlConnection = (HttpURLConnection)url.openConnection();
                InputStream inputStream = null;
                try {
                    inputStream = urlConnection.getInputStream();
                } catch (IOException e) {

                    return "null";
                    //e.printStackTrace();
                }
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
                BufferedReader reader = new BufferedReader(inputStreamReader);
                String line = reader.readLine();
                while (line != null){
                    result.append(line);
                    line = reader.readLine();
                }
                return result.toString();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                if(urlConnection != null){
                    urlConnection.disconnect();
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String s) {
            if(s.equals("null"))
            {
                Toast.makeText(MainActivity.this, "Не правильно введен город", Toast.LENGTH_SHORT).show();
            }
            else{
                super.onPostExecute(s);
                try {
                    JSONObject jsonObject = new JSONObject(s);
                    Log.i("tt", jsonObject.getString("cod"));
                    if(jsonObject.getString("cod").equals("200")){
                        String city = jsonObject.getString("name");
                        String temp = jsonObject.getJSONObject("main").getString("temp");
                        String description = jsonObject.getJSONArray("weather").getJSONObject(0).getString("description");
                        String weather = String.format("%s \n Температура: %s \n На улице: %s", city, temp, description);
                        textViewWeather.setText(weather);
                    }
                    else{
                        Toast.makeText(MainActivity.this, "Не верно введено название города", Toast.LENGTH_SHORT).show();

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}