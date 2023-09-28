import java.net.*;
import java.io.*;
import java.util.*;

public class APIAccessor {
    public static void main(String[] args) throws MalformedURLException, IOException {
        URL url = new URL("https://www.alphavantage.co/query?function=TIME_SERIES_DAILY&symbol=IBM&apikey=YVF8B9ZL4YQFAVXV");
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
