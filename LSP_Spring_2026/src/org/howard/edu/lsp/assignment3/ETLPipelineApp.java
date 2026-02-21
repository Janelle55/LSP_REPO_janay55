package org.howard.edu.lsp.assignment3;

import java.io.File;

/**
 * Runs the Assignment 3 ETL pipeline using the same behavior as Assignment 2.
 */
public class ETLPipelineApp {

  /**
   * Entry point for the program. Uses the same relative paths as Assignment 2.
   * @param args command-line args (not used)
   */
  public static void main(String[] args) {
    String inputPath = "data/products.csv";
    String outputPath = "data/transformed_products.csv";

    // Missing input file case: print clear message and exit cleanly
    if (!new File(inputPath).exists()) {
      System.out.println("Error: Input file not found at " + inputPath);
      return;
    }

    try {
      ETLPipeline pipeline = new ETLPipeline(
          new CsvProductReader(),
          new ProductTransformer(),
          new CsvProductWriter()
      );

      pipeline.run(inputPath, outputPath);
      System.out.println("Done! Wrote transformed file to: " + outputPath);

    } catch (Exception e) {
      // Keep it clean: no stack trace
      System.out.println("ETL failed: " + e.getMessage());
    }
  }
}