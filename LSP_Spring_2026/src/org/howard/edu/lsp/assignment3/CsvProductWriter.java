package org.howard.edu.lsp.assignment3;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

/**
 * Writes the transformed CSV output.
 */
public class CsvProductWriter {

  /**
   * Writes the output CSV file. Always writes header + PriceRange.
   */
  public void write(String outputPath, String header, List<TransformedProductRecord> rows) throws IOException {
    try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputPath))) {
      bw.write(header + ",PriceRange");
      bw.newLine();

      for (TransformedProductRecord r : rows) {
        bw.write(r.productId() + "," + r.name() + "," + r.price().toPlainString() + ","
            + r.category() + "," + r.priceRange());
        bw.newLine();
      }
    }
  }
}