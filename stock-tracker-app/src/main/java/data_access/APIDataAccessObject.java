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
import java.util.TreeMap;
import java.util.Map;
import java.util.Iterator;
import java.time.LocalDate;
import java.io.File;


//import java.io.InputStreamReader;
//import java.net.HttpURLConnection;
//import java.net.URL;

public class APIDataAccessObject {
        private static final String BASE_URL = "https://www.alphavantage.co/query";
        private static final String FUNCTION = "TIME_SERIES_DAILY";
        private static final String FX_FUNCTION = "FX_DAILY";
        private static final String CRYPTO_FUNCTION = "DIGITAL_CURRENCY_DAILY";

        private final HttpClient httpClient;
        private final ObjectMapper objectMapper;
        private String apiKey;

        public APIDataAccessObject() {
            this.httpClient = HttpClient.newHttpClient();
            this.objectMapper = new ObjectMapper();
            this.apiKey = readApiKeyFromFile("key.txt");
        }

        // this method will be used in the real program, but for testing purposes we will use the one below
        // we will have to change the name of this back to getHistoricalQuotes() at some point
        public TreeMap<Date, Double> getHistoricalQuotes(String symbol, String targetCurrency) {
            if (symbol.startsWith("$")) {
                return getHistoricalForexQuotes(symbol, targetCurrency);
            }
            else if (symbol.startsWith("#")) {
                return getHistoricalCryptoQuotes(symbol, targetCurrency);
            }
            else {
                return getHistoricalStockQuotes(symbol, targetCurrency);
            }
        }
        private TreeMap<Date, Double> getHistoricalForexQuotes(String symbol, String targetCurrency) {
            TreeMap<Date, Double> quotes = new TreeMap<>();
            try {
                String urlString = buildApiUrlForFX(symbol.substring(1), targetCurrency.substring(1));
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(urlString))
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                JsonNode root = objectMapper.readTree(response.body());

                JsonNode timeSeries = root.get("Time Series FX (Daily)");

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

        private TreeMap<Date, Double> getHistoricalCryptoQuotes(String symbol, String targetCurrency) {
            TreeMap<Date, Double> quotes = new TreeMap<>();
            try {
                String urlString = buildApiUrlForCrypto(symbol.substring(1), targetCurrency.substring(1));
                HttpRequest request = HttpRequest.newBuilder()
                        .uri(URI.create(urlString))
                        .build();

                HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
                JsonNode root = objectMapper.readTree(response.body());

                JsonNode timeSeries = root.get("Time Series FX (Daily)");

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

        private TreeMap<Date, Double> getHistoricalStockQuotes(String symbol, String targetCurrency) {
            TreeMap<Date, Double> conversionRates = new TreeMap<>();
            if (symbol.endsWith(".LON")) {
                conversionRates = getHistoricalForexQuotes("$GBP", targetCurrency);
            }
            else if (symbol.endsWith(".TRT") || symbol.endsWith(".TRV")) {
                conversionRates = getHistoricalForexQuotes("$CAD", targetCurrency);
            }
            else if (symbol.endsWith(".DEX")) {
                conversionRates = getHistoricalForexQuotes("$EUR", targetCurrency);
            }
            else if (symbol.endsWith(".BSE")) {
                conversionRates = getHistoricalForexQuotes("$INR", targetCurrency);
            }
            else if (symbol.endsWith(".SHH") || symbol.endsWith(".SHZ")) {
                conversionRates = getHistoricalForexQuotes("$CNY", targetCurrency);
            }
            else {
                conversionRates = getHistoricalForexQuotes("$USD", targetCurrency);
            }
            TreeMap<Date, Double> quotes = new TreeMap<>();
            try {
                String urlString = buildApiUrl(symbol);
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
                    if (conversionRates.containsKey(date)) {
                        price *= conversionRates.get(date); // TODO: make sure conversion is in correct direction lol
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
        public TreeMap<Date, Double> getHistoricalQuotes(String symbol) {
            TreeMap<Date, Double> quotes = new TreeMap<>();
            try {
                String urlString = buildApiUrl(symbol);
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
                    double price = entry.getValue().get("4. close").asDouble();
                    quotes.put(date, price);
                }
            } catch (IOException | ParseException e) {
                e.printStackTrace(); // TODO: handle exception
            }
            return quotes;
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

        private String buildApiUrl(String symbol) {
            return String.format(
                    "%s?function=%s&symbol=%s&apikey=%s&outputsize=full&datatype=json",
                    BASE_URL, FUNCTION, symbol, apiKey);
        }

        private String buildApiUrlForFX(String fromCurrency, String toCurrency) {
            return String.format(
                "%s?function=%s&from_symbol=%s&to_symbol=%s&apikey=%s&outputsize=full&datatype=json",
                BASE_URL, FX_FUNCTION, fromCurrency, toCurrency, apiKey);
        }

        private String buildApiUrlForCrypto(String symbol, String market) {
            return String.format(
                "%s?function=%s&from_symbol=%s&to_symbol=%s&apikey=%s&outputsize=full&datatype=json",
                BASE_URL, CRYPTO_FUNCTION, symbol, market, apiKey);
        }
}
