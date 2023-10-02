import java.net.*;
import java.io.*;
import java.util.*;

public class APIAccessor {
    public static void main(String[] args) throws MalformedURLException, IOException {
        BufferedReader apiKeyReader = new BufferedReader(new FileReader("key.txt"));
        String key = apiKeyReader.readLine();
        String urlString = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=TSLA&apikey=" + key;
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String request = "";
        String line;
        while ((line = br.readLine()) != null)
            request = request + line + "\n";
        System.out.println(request);
    }
}
