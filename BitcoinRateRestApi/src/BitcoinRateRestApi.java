package ITionSolutions.BitcoinRateRestApi.src;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class BitcoinRateRestApi {

    public static void main(String[] args) {
        try {
            String apiUrl = "https://api.coindesk.com/v1/bpi/currentprice.json";
            String json = sendGET(apiUrl);
            double rate = extractBitcoinRate(json);
            String rateInWords = convertToWords(rate);
            System.out.println(rateInWords);
        } catch (IOException e) {
            System.out.println("An error occurred while retrieving the Bitcoin rate.");
            e.printStackTrace();
        }
    }

    private static String sendGET(String apiUrl) throws IOException {
        URL url = new URL(apiUrl);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        int responseCode = connection.getResponseCode();
        if (responseCode == HttpURLConnection.HTTP_OK) {
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;
            StringBuilder response = new StringBuilder();
            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();
            return response.toString();
        } else {
            throw new IOException("Failed to retrieve data from the API. Response code: " + responseCode);
        }
    }

    private static double extractBitcoinRate(String json) {
        JSONObject jsonObject = new JSONObject(json);
        JSONObject bpi = jsonObject.getJSONObject("bpi");
        JSONObject usd = bpi.getJSONObject("USD");
        String rateString = usd.getString("rate");
        return Double.parseDouble(rateString.replace(",", ""));
    }

    private static String convertToWords(double rate) {
        int rateInInt = (int) rate;
        String[] units = {
                "", "One", "Two", "Three", "Four", "Five", "Six", "Seven", "Eight", "Nine", "Ten",
                "Eleven", "Twelve", "Thirteen", "Fourteen", "Fifteen", "Sixteen", "Seventeen", "Eighteen", "Nineteen"
        };
        String[] tens = {
                "", "", "Twenty", "Thirty", "Forty", "Fifty", "Sixty", "Seventy", "Eighty", "Ninety"
        };

        if (rateInInt == 0) {
            return "Zero";
        }

        if (rateInInt < 0) {
            return "Minus " + convertToWords(Math.abs(rateInInt));
        }

        String words = "";

        if ((rateInInt / 1000) > 0) {
            words += convertToWords(rateInInt / 1000) + " Thousand ";
            rateInInt %= 1000;
        }

        if ((rateInInt / 100) > 0) {
            words += convertToWords(rateInInt / 100) + " Hundred ";
            rateInInt %= 100;
        }

        if (rateInInt > 0) {
            if (rateInInt < 20) {
                words += units[rateInInt];
            } else {
                words += tens[rateInInt / 10];
                if ((rateInInt % 10) > 0) {
                    words += " " + units[rateInInt % 10];
                }
            }
        }

        return words;
    }
}
