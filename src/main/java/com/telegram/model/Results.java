package com.telegram.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class Results {
    String name;
    double todayPercent;
    double todayProfit;
    double balanceBuy;
    double balanceNow;
    double profitPercent;
    double profit;
    String currency;

    @Override
    public String toString() {
        return "Name = " + name + ",\n" +
                "Today Percent = " + String.format("%.2f",todayPercent)  +" %,\n" +
                "Today Profit = " + String.format("%.2f",todayProfit) +" "+ currency + ",\n" +
                "Average purchase price = " + String.format("%.3f",balanceBuy) + " "+ currency + ",\n" +
                "Cost now = " + String.format("%.2f",balanceNow) + " "+ currency + ",\n" +
                "Profit Percent = " + String.format("%.3f",profitPercent) + " %,\n" +
                "Profit = " + String.format("%.2f",profit) + " "+ currency + ",\n";

    }
}
