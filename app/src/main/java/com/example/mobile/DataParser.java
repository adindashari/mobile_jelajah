package com.example.mobile;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class DataParser extends AsyncTask<Void, Void, Integer> {

    Context context;
    Spinner spinner;
    String jsonData;

    ProgressDialog progressDialog;
    ArrayList<String> dataObject = new ArrayList<>();

    public DataParser(Context context, Spinner spinner, String jsonData) {
        this.context = context;
        this.spinner = spinner;
        this.jsonData = jsonData;
    }


    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog = new ProgressDialog(context);
        progressDialog.setTitle("Parse");
        progressDialog.setMessage("Parsing... Please Wait");
        progressDialog.show();
    }

    @Override
    protected Integer doInBackground(Void...params) {
        return this.parseData();
    }

    private Integer parseData(){
        try {
            JSONArray jsonArray = new JSONArray(jsonData);
            JSONObject jsonObject = null;

            dataObject.clear();
            ListPerson dObject = null;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                String name = jsonObject.getString("name");
                String age = jsonObject.getString("age");
                String city = jsonObject.getString("city");

                spinner = new ListPerson();
                spinner.setName(name);
                spinner.setAge(age);
                spinner.setCity(city);

                dataObject.add(name);

                return 1;

            }


        } catch (JSONException e) {
            e.printStackTrace();
        }

        return 0;
    }



    @Override
    protected void onPostExecute(Integer result) {
        super.onPostExecute(result);

        progressDialog.dismiss();

        if (result == 0) {
            Toast.makeText(context, "Gagal parsing data", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(context, "Parsing data sukses", Toast.LENGTH_SHORT).show();

            ArrayAdapter arrayAdapter = new ArrayAdapter(context, android.R.layout.simple_list_item_1, dataObject);
            spinner.setAdapter(arrayAdapter);
        }



    }
}
