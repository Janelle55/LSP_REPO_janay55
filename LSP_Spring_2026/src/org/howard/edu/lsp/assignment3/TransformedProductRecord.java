package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;

/**
 * Represents one transformed output row (includes PriceRange).
 */
public record TransformedProductRecord(
    String productId,
    String name,
    BigDecimal price,
    String category,
    String priceRange
) {}