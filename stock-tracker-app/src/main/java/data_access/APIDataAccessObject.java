package data_access;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.core.Versioned;
import com.fasterxml.jackson.core.TreeNode;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;

public class APIDataAccessObject {
        private static final String BASE_URL = "https://www.alphavantage.co/query";
        private static final String FUNCTION = "TIME_SERIES_DAILY";

        private final HttpClient httpClient;
        private final ObjectMapper objectMapper;
        private String apiKey;

        public APIDataAccessObject() {
            this.httpClient = HttpClient.newHttpClient();
            this.objectMapper = new ObjectMapper();
            this.apiKey = readApiKeyFromFile("key.txt");
        }

        private String readApiKeyFromFile(String filename) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader(filename));
                String key = reader.readLine();
                reader.close();
                return key;
            } catch (IOException e) {
                e.printStackTrace(); // no key in file
            }
            return null;
        }
        public HashMap<Date, Double> getHistoricalQuotes(String symbol, Date startDate, Date endDate) {
            HashMap<Date, Double> quotes = new HashMap<>();
            try {
                String urlString = buildApiUrl(symbol, startDate, endDate);
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(urlString))
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                JsonNode root = objectMapper.readTree(response.body());

                JsonNode timeSeries = root.get("Time Series (Daily)");

                Iterator<Map.Entry<String, JsonNode>> fields = timeSeries.fields();
                while(fields.hasNext()) {
                    Map.Entry<String, JsonNode> entry = fields.next();
                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(entry.getKey());
                    double price = entry.getValue().get("4. close").asDouble();
                    quotes.put(date, price);
                }
            } catch (IOException | InterruptedException | ParseException e) {
                e.printStackTrace(); // TODO: handle exception
            }
            return quotes;
        }

        private String buildApiUrl(String symbol, Date startDate, Date endDate) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String startDateString = dateFormat.format(startDate);
            String endDateString = dateFormat.format(endDate); 
            return String.format(
                    "%s?function=%s&symbol=%s&apikey=%s&outputsize=full&datatype=json&startDate=%s&endDate=%s",
                    BASE_URL, FUNCTION, symbol, apiKey, startDateString, endDateString);
        }

/*
        }



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
*/
}
