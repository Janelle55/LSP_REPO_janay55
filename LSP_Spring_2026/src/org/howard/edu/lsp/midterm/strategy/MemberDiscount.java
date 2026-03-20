package org.howard.edu.lsp.midterm.strategy;

public class MemberDiscount implements DiscountStrategy {
    @Override
    public double calculate(double price) {
        return price * 0.90;
    }
}