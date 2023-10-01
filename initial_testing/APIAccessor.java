import java.net.*;
import java.io.*;
import java.util.*;

public class APIAccessor {
    public static void main(String[] args) throws MalformedURLException, IOException {
        File f = new File("key.txt");
        BufferedReader apiKeyReader = new BufferedReader(new FileReader("key.txt"));
        String key = apiKeyReader.readLine();
        String urlString = "https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=IBM&apikey=" + key;
        URL url = new URL(urlString);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        int responseCode = connection.getResponseCode();
        System.out.println("Response Code: " + responseCode);

        BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        for(int i = 0; i < 10; i++)
            System.out.println(br.readLine());
//        StringBuilder response = new StringBuilder();
//
//        while ((inputLine = in.readLine()) != null) {
//            response.append(inputLine);
//        }
        br.close();
//
//        System.out.println("Response Data:\n" + response.toString());
    }
}
