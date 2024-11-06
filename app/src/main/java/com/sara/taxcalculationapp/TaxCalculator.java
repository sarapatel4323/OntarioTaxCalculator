package com.sara.taxcalculationapp;

public class TaxCalculator {

    private static final double RRSP_LIMIT_NEXT_YEAR = 31560; // Example limit for the next year

    public double calculateFederalTax(double income) {
        double tax = 0;
        // Federal tax brackets for 2024
        if (income <= 53600) {
            tax = income * 0.15;
        } else if (income <= 106000) {
            tax = 8040 + (income - 53600) * 0.205;
        } else if (income <= 165000) {
            tax = 17468 + (income - 106000) * 0.26;
        } else if (income <= 235000) {
            tax = 30552 + (income - 165000) * 0.29;
        } else {
            tax = 51614 + (income - 235000) * 0.33;
        }
        return tax;
    }

    public double calculateProvincialTax(double income) {
        double tax = 0;
        // Ontario provincial tax brackets for 2024
        if (income <= 49020) {
            tax = income * 0.0505;
        } else if (income <= 98040) {
            tax = 2474 + (income - 49020) * 0.0915;
        } else if (income <= 150000) {
            tax = 8592 + (income - 98040) * 0.1116;
        } else if (income <= 220000) {
            tax = 16030 + (income - 150000) * 0.1216;
        } else {
            tax = 30638 + (income - 220000) * 0.1316;
        }
        return tax;
    }

    public double getRrspLimitNextYear() {
        return RRSP_LIMIT_NEXT_YEAR;
    }
}
