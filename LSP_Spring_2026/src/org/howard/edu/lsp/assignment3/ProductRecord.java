package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/**
 * Represents one valid input product row.
 */
public record ProductRecord(String productId, String name, BigDecimal price, String category) {}