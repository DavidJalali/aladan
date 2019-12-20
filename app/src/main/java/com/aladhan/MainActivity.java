package com.aladhan;

import androidx.appcompat.app.AppCompatActivity;

import android.net.http.HttpResponseCache;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button Btn = (Button) findViewById(R.id.btn_search);

        Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               final EditText et = (EditText) findViewById(R.id.etsearch);
                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            URL obj = new URL("http://api.aladhan.com/v1/calendarByCity?city="+et.getText()+"&country=IR&method=8&month=12&year=2019");
                            HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                            con.setRequestMethod("GET");
                             con.setRequestProperty("User-Agent", "Mozila/5.0");
                            int responseCode = con.getResponseCode();
                            if (responseCode == HttpURLConnection.HTTP_OK) {
                                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                                String inputLine = null;
                                StringBuffer response = new StringBuffer();

                                while((inputLine = in.readLine()) != null){
                                    response.append(inputLine);
                                }

                                JSONObject response_obj = new JSONObject(response.toString());
                                String query = response_obj.getString("Fajr");
                                query = "";
                            }


                        } catch (MalformedURLException e) {
                            e.getMessage();
                        } catch (IOException e) {
                            e.getMessage();
                        }
                        catch (JSONException e) {
                            e.getMessage();
                        }
                    }
                });
                thread.start();
            }
        });

    }
}
