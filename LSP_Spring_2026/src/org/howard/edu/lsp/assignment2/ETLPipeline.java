package org.howard.edu.lsp.assignment2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ETLPipeline {

    public static void main(String[] args) {

        // This is the file I’m reading from (input file my program will extract data from)
        String inputPath = "data/products.csv";

        // This is the new file I’m writing the transformed data to
        String outputPath = "data/products_transformed.csv";

        try {
            // Running the ETL process (Extract → Transform → Load)
            runETL(inputPath, outputPath);

            System.out.println("Done! Wrote transformed file to: " + outputPath);

        } catch (IOException e) {

            // If anything goes wrong reading/writing files, it will show here
            System.out.println("ETL failed: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void runETL(String inputPath, String outputPath) throws IOException {

        // BufferedReader = reads the CSV file
        // BufferedWriter = writes the new transformed CSV file
        try (BufferedReader br = new BufferedReader(new FileReader(inputPath));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath))) {

            // Reading the first row (header)
            // Assignment says header should NOT be transformed
            String header = br.readLine();

            if (header == null) {
                throw new IOException("Input file is empty.");
            }

            // Writing the same header but adding the new field “PriceRange”
            bw.write(header + ",PriceRange");
            bw.newLine();

            String line;

            // Looping through the rest of the rows in the CSV
            while ((line = br.readLine()) != null) {

                // Skipping empty rows just in case
                if (line.trim().isEmpty()) continue;

                // Splitting each row by comma to get the columns
                String[] parts = line.split(",", -1);

                // If the row doesn’t have exactly 4 columns, skip it
                if (parts.length != 4) {
                    continue;
                }

                // Extracting each column into variables
                String productId = parts[0].trim();
                String name = parts[1].trim();
                String priceStr = parts[2].trim();
                String category = parts[3].trim();

                // Converting price from String → BigDecimal for calculations
                BigDecimal price = new BigDecimal(priceStr);

                // 1. Converting product name to UPPERCASE
                name = name.toUpperCase();

                // Keeping the original category for later condition checking
                String originalCategory = category;

                // 2. If category is Electronics → apply 10% discount
                if (category.equalsIgnoreCase("Electronics")) {
                    price = price.multiply(new BigDecimal("0.90"));
                }

                // Rounding price to 2 decimal places
                BigDecimal roundedPrice =
                        price.setScale(2, RoundingMode.HALF_UP);

                // 3. If discounted price > 500 AND original category was Electronics
                // change category to Premium Electronics
                if (originalCategory.equalsIgnoreCase("Electronics")
                        && roundedPrice.compareTo(new BigDecimal("500.00")) > 0) {

                    category = "Premium Electronics";
                }

                // 4. Determining the price range based on final price
                String priceRange = getPriceRange(roundedPrice);


                // Writing the transformed data into the new CSV file
                bw.write(productId + "," + name + "," +
                        roundedPrice.toPlainString() + "," +
                        category + "," + priceRange);

                bw.newLine();
            }
        }
    }

    private static String getPriceRange(BigDecimal price) {

        // Categorizing price into ranges

        if (price.compareTo(new BigDecimal("10.00")) <= 0) {
            return "Low";

        } else if (price.compareTo(new BigDecimal("100.00")) <= 0) {
            return "Medium";

        } else {
            return "High";
        }
    }
}
