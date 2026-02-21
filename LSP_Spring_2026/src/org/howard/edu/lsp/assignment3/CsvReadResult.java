package org.howard.edu.lsp.assignment3;

import java.util.List;

/**
 * Simple container for the CSV header and parsed records.
 */
public record CsvReadResult(String header, List<ProductRecord> records) {}