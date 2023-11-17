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
import java.time.ZoneId;
import java.util.Date;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.time.LocalDate;
import java.io.File;


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

        // this method will be used in the real program, but for testing purposes we will use the one below
        // we will have to change the name of this back to getHistoricalQuotes() at some point
        public HashMap<Date, Double> getHistoricalQuotesReal(String symbol, Date startDate, Date endDate) {
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
                    if ((date.after(startDate) || date.equals(startDate)) && (date.before(endDate) || date.equals(endDate))) {
                        double price = entry.getValue().get("4. close").asDouble();
                        quotes.put(date, price);
                    }
                }
            } catch (IOException | InterruptedException | ParseException e) {
                e.printStackTrace(); // TODO: handle exception
            }
            return quotes;
        }


        // this method is for testing purposes only, it reads from a local file instead of making an API call
        // the real is above and will have to have its name changed to getHistoricalQuotes
        public HashMap<Date, Double> getHistoricalQuotes(String symbol, Date startDate, Date endDate) {
            HashMap<Date, Double> quotes = new HashMap<>();
            try {
                String urlString = buildApiUrl(symbol, startDate, endDate);
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(urlString))
                        .build();

                JsonNode root;
                if (symbol.equals("IBM")) {
                    String filePath = "test_queries/IBM.json";
                    ObjectMapper objectMapper = new ObjectMapper();
                    root = objectMapper.readTree(new File(filePath));
                }
                else {
                    String filePath = "test_queries/SHOP-TO.json";
                    ObjectMapper objectMapper = new ObjectMapper();
                    root = objectMapper.readTree(new File(filePath));
                }

                JsonNode timeSeries = root.get("Time Series (Daily)");

                Iterator<Map.Entry<String, JsonNode>> fields = timeSeries.fields();
                while(fields.hasNext()) {
                    Map.Entry<String, JsonNode> entry = fields.next();
                    Date date = new SimpleDateFormat("yyyy-MM-dd").parse(entry.getKey());
                    if ((date.after(startDate) || date.equals(startDate)) && (date.before(endDate) || date.equals(endDate))) {
                        double price = entry.getValue().get("4. close").asDouble();
                        quotes.put(date, price);
                    }
                }
            } catch (IOException | ParseException e) {
                e.printStackTrace(); // TODO: handle exception
            }
            return quotes;
        }


        // gets all historical quotes for a given symbol
        public HashMap<Date, Double> getHistoricalQuotes(String symbol) {
            getHistoricalQuotes(symbol, new Date(0, 0, 1), new Date(Integer.MAX_VALUE, 11, 31));
        }

        private String buildApiUrl(String symbol, Date startDate, Date endDate) {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            String startDateString = dateFormat.format(startDate);
            String endDateString = dateFormat.format(endDate); 
            return String.format(
                    "%s?function=%s&symbol=%s&apikey=%s&outputsize=full&datatype=json",
                    BASE_URL, FUNCTION, symbol, apiKey);
        }
}
