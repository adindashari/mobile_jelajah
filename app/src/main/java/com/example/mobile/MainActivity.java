package com.example.mobile;

import androidx.appcompat.app.AppCompatActivity;


import android.os.Bundle;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.Log;
import android.widget.Adapter;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;
import java.util.List;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    RecyclerView listPerson;
    List<ListPerson> listPersons;
    private static String JSON_URL = "http://localhost/backend/index.php/webrestcontroller/persons";
    Adapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listPerson = findViewById(R.id.listPerson);
        listPersons = new ArrayList<>();
        extractListPerson();

    }

    private void extractListPerson() {
        RequestQueue queue = Volley.newRequestQueue(this);
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, JSON_URL, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject listObject = response.getJSONObject(i);

                        ListPerson listPerson = new ListPerson();
                        listPerson.setName(listObject.getString("name").toString());
                        listPerson.setAge(listObject.getString("age").toString());
                        listPerson.setCity(listObject.getString("city").toString());
                        listPersons.add(listPerson);

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                listPerson.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                adapter = new Adapter(getApplicationContext(), listPersons);
                listPerson.setAdapter(adapter);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.d("tag", "onErrorResponse: " + error.getMessage());
            }
        });

    }
}
