package org.howard.edu.lsp.assignment3;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Reads products from a CSV file and skips invalid rows (robust behavior).
 */
public class CsvProductReader {

  /**
   * Reads the CSV file and returns header + valid parsed records.
   * Header is not transformed.
   */
  public CsvReadResult read(String inputPath) throws IOException {
    try (BufferedReader br = new BufferedReader(new FileReader(inputPath))) {

      String header = br.readLine();

      // Empty input case: still return a default header (pipeline will write header-only output)
      if (header == null) {
        header = "ProductID,Name,Price,Category";
        return new CsvReadResult(header, new ArrayList<>());
      }

      List<ProductRecord> records = new ArrayList<>();
      String line;

      while ((line = br.readLine()) != null) {
        if (line.trim().isEmpty()) continue;

        String[] parts = line.split(",", -1);
        if (parts.length != 4) continue;

        String productId = parts[0].trim();
        String name = parts[1].trim();
        String priceStr = parts[2].trim();
        String category = parts[3].trim();

        if (productId.isEmpty() || name.isEmpty() || priceStr.isEmpty() || category.isEmpty()) continue;

        // Skip bad price rows instead of crashing
        BigDecimal price;
        try {
          price = new BigDecimal(priceStr);
        } catch (NumberFormatException e) {
          continue;
        }

        records.add(new ProductRecord(productId, name, price, category));
      }

      return new CsvReadResult(header, records);
    }
  }
}