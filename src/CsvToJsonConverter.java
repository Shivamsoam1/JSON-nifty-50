import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class CsvToJsonConverter {
    public static void main(String[] args) {
        String inputFile = "ind_nifty50list.csv";
        String outputFile = "filtered_nifty50.json";
        List<StockInfo> stockList = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            String line = br.readLine(); // Skip header

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if(parts.length >= 5) {
                    String companyName = parts[0].trim();
                    String symbol = parts[2].trim();
                    String isinCode = parts[4].trim();
                    StockInfo stock = new StockInfo(companyName, symbol, isinCode);
                    stockList.add(stock);
                }
            }
            br.close();

            
            Gson gson = new GsonBuilder().setPrettyPrinting().create();
            String json = gson.toJson(stockList);

            
            FileWriter writer = new FileWriter(outputFile);
            writer.write(json);
            writer.close();

            System.out.println("Filtered JSON created: " + outputFile);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
