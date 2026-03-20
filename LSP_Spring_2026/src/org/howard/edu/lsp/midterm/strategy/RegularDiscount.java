package org.howard.edu.lsp.midterm.strategy;

public class RegularDiscount implements DiscountStrategy {
    @Override
    public double calculate(double price) {
        return price;
    }
}