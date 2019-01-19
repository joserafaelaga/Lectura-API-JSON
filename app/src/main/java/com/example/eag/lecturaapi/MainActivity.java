package com.example.eag.lecturaapi;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    TextView texto;

    private RequestQueue mQueue;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        texto = (TextView) findViewById(R.id.texto);

        mQueue = Volley.newRequestQueue(this);
    }



    public void leer(View view){
        String url = "https://www.w3schools.com/angular/customers.php";

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {

                try {
                    JSONArray jsonArray = response.getJSONArray("records");

                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject empleados = jsonArray.getJSONObject(i);

                        String nombre = empleados.getString("Name");
                        String ciudad = empleados.getString("City");
                        String pais = empleados.getString("Country");

                        texto.append(nombre + "," + ciudad + "," + pais + "\n\n");
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        }

        );

        mQueue.add(jsonObjectRequest);


    } //Fin método
}
