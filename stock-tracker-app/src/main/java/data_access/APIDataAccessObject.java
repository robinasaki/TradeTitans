package data_access;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class APIDataAccessObject {
        private static final String API_KEY = "../../../../../../key.txt";
        private static final String BASE_URL = "https://www.alphavantage.co/query";
        private static final String QUOTE_ENDPOINT = "?function=TIME_SERIES_DAILY&symbol=%s&apikey=%s";

        public static String getQuote(String symbol) {
            String urlString = BASE_URL + String.format(QUOTE_ENDPOINT, symbol, API_KEY);
            try {
                URL url = new URL(urlString);            
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setRequestMethod("GET");

                int responseCode = con.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String inputLine;
                    StringBuffer content = new StringBuffer();
                    while((inputLine = in.readLine()) != null) {
                        content.append(inputLine);
                    }
                    in.close();
                    return content.toString();
                } else {
                    System.out.println("GET request failed");
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
    }
}
