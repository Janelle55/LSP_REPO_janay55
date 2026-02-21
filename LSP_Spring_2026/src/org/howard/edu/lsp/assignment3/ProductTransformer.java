package org.howard.edu.lsp.assignment3;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

/**
 * Applies the ETL transformations (same exact rules as Assignment 2).
 */
public class ProductTransformer {

  /**
   * Transforms a list of product records.
   */
  public List<TransformedProductRecord> transformAll(List<ProductRecord> records) {
    List<TransformedProductRecord> out = new ArrayList<>();
    for (ProductRecord r : records) {
      out.add(transformOne(r));
    }
    return out;
  }

  /**
   * Transforms one record: uppercase name, discount electronics, rounding,
   * premium electronics check, and pricerange.
   */
  public TransformedProductRecord transformOne(ProductRecord r) {

    String name = r.name().toUpperCase();
    String category = r.category();
    String originalCategory = category;

    BigDecimal price = r.price();

    if (category.equalsIgnoreCase("Electronics")) {
      price = price.multiply(new BigDecimal("0.90"));
    }

    BigDecimal rounded = price.setScale(2, RoundingMode.HALF_UP);

    if (originalCategory.equalsIgnoreCase("Electronics")
        && rounded.compareTo(new BigDecimal("500.00")) > 0) {
      category = "Premium Electronics";
    }

    String priceRange = getPriceRange(rounded, category);

    return new TransformedProductRecord(r.productId(), name, rounded, category, priceRange);
  }

  private String getPriceRange(BigDecimal price, String category) {
    // Match your A2 behavior: Premium Electronics => Premium
    if (category.equalsIgnoreCase("Premium Electronics")) {
      return "Premium";
    }

    if (price.compareTo(new BigDecimal("10.00")) <= 0) return "Low";
    if (price.compareTo(new BigDecimal("100.00")) <= 0) return "Medium";
    return "High";
  }
}