package org.howard.edu.lsp.assignment3;

import java.util.List;

/**
 * Coordinates Extract → Transform → Load using helper classes.
 */
public class ETLPipeline {
  private final CsvProductReader reader;
  private final ProductTransformer transformer;
  private final CsvProductWriter writer;

  /**
   * Creates a pipeline with injected components.
   */
  public ETLPipeline(CsvProductReader reader, ProductTransformer transformer, CsvProductWriter writer) {
    this.reader = reader;
    this.transformer = transformer;
    this.writer = writer;
  }

  /**
   * Runs the pipeline end-to-end.
   */
  public void run(String inputPath, String outputPath) throws Exception {
    CsvReadResult result = reader.read(inputPath);

    // Transform all valid records
    List<TransformedProductRecord> transformed = transformer.transformAll(result.records());

    // Always write output (including header-only output for empty input)
    writer.write(outputPath, result.header(), transformed);
  }
}