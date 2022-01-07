package com.telegram.model;

import lombok.AllArgsConstructor;
import lombok.Value;

@Value
@AllArgsConstructor
public class MyPortfolio {
    String figi;
    String ticker;
    String instrumentType;
    String name;
    double balance;
    String currency;
    double valueUp;
    double valueBuy;
    double priceNow;
    double priceClose;

    @Override
    public String toString() {
        return  "Name = " + name + ",\n" +
                "Ticker = " + ticker + ",\n" +
                "Instrument Type = " + instrumentType + ",\n" +
                "Quantity = " + balance + ",\n" +
                "Average purchase price = " + valueBuy + " "+ currency + ",\n" +
                "Price now = " + priceNow + " "+ currency + ",\n" +
                "Price close = " + priceClose + " "+ currency + ",\n";
    }
}
