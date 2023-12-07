package data_access;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.JsonNode;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.SimpleDateFormat;
import java.time.ZoneId;
import java.util.*;
import java.text.ParseException;
import java.time.LocalDate;

import use_case.APIDataAccessInterface;

public class APIDataAccessObject implements APIDataAccessInterface {
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

    public TreeMap<Date, Double> getHistoricalQuotes(String symbol, String targetCurrency) {
        // if symbol and targetCurrency are the same, return tree map of 1's for every day since 1900
        if (symbol.equals(targetCurrency)) {
            TreeMap<Date, Double> quotes = new TreeMap<>();
            LocalDate date = LocalDate.of(1900, 1, 1);
            while (date.isBefore(LocalDate.now())) {
                quotes.put(Date.from(date.atStartOfDay(ZoneId.systemDefault()).toInstant()), 1.0);
                date = date.plusDays(1);
            }
            return quotes;
        }
        if (symbol.startsWith("$")) {
            return getHistoricalForexQuotes(symbol, targetCurrency);
        } else if (symbol.startsWith("#")) {
            return getHistoricalCryptoQuotes(symbol, targetCurrency);
        } else {
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
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                Date date = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(entry.getKey());
                double price = entry.getValue().get("4. close").asDouble();
                quotes.put(date, price);
            }
        } catch (IOException | InterruptedException | ParseException e) {
            e.printStackTrace();
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

            JsonNode timeSeries = root.get("Time Series (Digital Currency Daily)");

            Iterator<Map.Entry<String, JsonNode>> fields = timeSeries.fields();
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                Date date = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH).parse(entry.getKey());
                double price = entry.getValue().get("4a. close (" + targetCurrency.substring(1) + ")").asDouble();
                quotes.put(date, price);
            }
        } catch (IOException | InterruptedException | ParseException e) {
            e.printStackTrace();
        }
        return quotes;
    }

    private TreeMap<Date, Double> getHistoricalStockQuotes(String symbol, String targetCurrency) {
        TreeMap<Date, Double> conversionRates = new TreeMap<>();
        if (symbol.endsWith(".LON")) {
            conversionRates = getHistoricalForexQuotes("$GBP", targetCurrency);
        } else if (symbol.endsWith(".TRT") || symbol.endsWith(".TRV")) {
            conversionRates = getHistoricalForexQuotes("$CAD", targetCurrency);
        } else if (symbol.endsWith(".DEX")) {
            conversionRates = getHistoricalForexQuotes("$EUR", targetCurrency);
        } else if (symbol.endsWith(".BSE")) {
            conversionRates = getHistoricalForexQuotes("$INR", targetCurrency);
        } else if (symbol.endsWith(".SHH") || symbol.endsWith(".SHZ")) {
            conversionRates = getHistoricalForexQuotes("$CNY", targetCurrency);
        } else {
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
            while (fields.hasNext()) {
                Map.Entry<String, JsonNode> entry = fields.next();
                Date date = new SimpleDateFormat("yyyy-MM-dd").parse(entry.getKey());
                double price = entry.getValue().get("4. close").asDouble();
                if (conversionRates.containsKey(date)) {
                    price *= conversionRates.get(date);
                    quotes.put(date, price);
                }
            }
        } catch (IOException | InterruptedException | ParseException e) {
            e.printStackTrace();
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
                "%s?function=%s&symbol=%s&outputsize=full&apikey=%s",
                BASE_URL, FUNCTION, symbol, apiKey);
    }

    private String buildApiUrlForFX(String fromCurrency, String toCurrency) {
        return String.format(
                "%s?function=%s&from_symbol=%s&to_symbol=%s&outputsize=full&apikey=%s",
                BASE_URL, FX_FUNCTION, fromCurrency, toCurrency, apiKey);
    }

    private String buildApiUrlForCrypto(String symbol, String market) {
        return String.format(
                "%s?function=%s&symbol=%s&market=%s&apikey=%s",
                BASE_URL, CRYPTO_FUNCTION, symbol, market, apiKey);
    }
}
