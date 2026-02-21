package org.howard.edu.lsp.assignment2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;

public class ETLPipeline {

    public static void main(String[] args) {

        // This is the file I’m reading from (input file my program will extract data from)
        String inputPath = "data/products.csv";

        // This is the output file the assignment expects
        String outputPath = "data/transformed_products.csv";

        // If the input file is missing, I want to exit cleanly with a clear message
        try {
            runETL(inputPath, outputPath);
            System.out.println("Done! Wrote transformed file to: " + outputPath);
        } catch (FileNotFoundException e) {
            System.out.println("Error: Input file not found at " + inputPath);
        } catch (IOException e) {
            System.out.println("ETL failed: " + e.getMessage());
        }
    }

    private static void runETL(String inputPath, String outputPath) throws IOException {

        // BufferedReader reads the CSV
        // BufferedWriter writes the transformed CSV
        try (BufferedReader br = new BufferedReader(new FileReader(inputPath));
             BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath))) {

            // Reading the header (I do not transform the header)
            String header = br.readLine();

            // If the file is empty, I still need to output a header-only file
            if (header == null) {
                bw.write("ProductID,Name,Price,Category,PriceRange");
                bw.newLine();
                return;
            }

            // Writing header + the new column PriceRange
            bw.write(header + ",PriceRange");
            bw.newLine();

            String line;

            // Looping through every row after the header
            while ((line = br.readLine()) != null) {

                // Skipping blank lines
                if (line.trim().isEmpty()) {
                    continue;
                }

                // Splitting into columns
                String[] parts = line.split(",", -1);

                // If it doesn’t have 4 columns, it’s not valid for this assignment, so I skip it
                if (parts.length != 4) {
                    System.out.println("Skipping invalid row (wrong column count): " + line);
                    continue;
                }

                // Extracting each column
                String productId = parts[0].trim();
                String name = parts[1].trim();
                String priceStr = parts[2].trim();
                String category = parts[3].trim();

                // If any required field is missing, I skip it
                if (productId.isEmpty() || name.isEmpty() || priceStr.isEmpty() || category.isEmpty()) {
                    System.out.println("Skipping invalid row (missing field): " + line);
                    continue;
                }

                // Converting price safely (if price is like "abc", I skip it instead of crashing)
                BigDecimal price;
                try {
                    price = new BigDecimal(priceStr);
                } catch (NumberFormatException e) {
                    System.out.println("Skipping invalid row (bad price): " + line);
                    continue;
                }

                // 1) Product name must be uppercase
                name = name.toUpperCase();

                // Keeping the original category so I can check it even after edits
                String originalCategory = category;

                // 2) If category is Electronics → apply 10% discount
                if (category.equalsIgnoreCase("Electronics")) {
                    price = price.multiply(new BigDecimal("0.90"));
                }

                // Rounding the final numeric price to 2 decimals (HALF_UP)
                BigDecimal roundedPrice = price.setScale(2, RoundingMode.HALF_UP);

                // 3) If discounted price > 500 AND original category was Electronics → Premium Electronics
                if (originalCategory.equalsIgnoreCase("Electronics")
                        && roundedPrice.compareTo(new BigDecimal("500.00")) > 0) {
                    category = "Premium Electronics";
                }

                // 4) PriceRange based on the final rounded price
                String priceRange = getPriceRange(roundedPrice, category);

                // Writing the transformed row
                bw.write(productId + "," + name + "," +
                        roundedPrice.toPlainString() + "," +
                        category + "," + priceRange);
                bw.newLine();
            }
        }
    }

    private static String getPriceRange(BigDecimal price, String category) {

        // If it’s Premium Electronics, I label it as Premium no matter what
        if (category.equalsIgnoreCase("Premium Electronics")) {
            return "Premium";
        }

        // Otherwise I categorize based on price thresholds
        if (price.compareTo(new BigDecimal("10.00")) <= 0) {
            return "Low";
        } else if (price.compareTo(new BigDecimal("100.00")) <= 0) {
            return "Medium";
        } else {
            return "High";
        }
    }
}