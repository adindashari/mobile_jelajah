package com.example.mobile;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class Connection {
    public static HttpURLConnection connection (String urlAddress) throws IOException {
        try {
            URL url = new URL(urlAddress);
            HttpURLConnection con = (HttpURLConnection) url.openConnection();

            con.setRequestMethod("GET");
            con.setConnectTimeout(20000);
            con.setReadTimeout(20000);
            con.setDoInput(true);

            return con;
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return null;

    }
}
