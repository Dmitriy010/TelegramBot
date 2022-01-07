package com.telegram.currency;

public enum Currency {
    RUB("RUB"),
    USD("USD"),
    EUR("EUR"),
    GBP("GBP"),
    HKD("HKD"),
    CHF("CHF"),
    JPY("JPY"),
    TRY("TRY");


    private String currency;

    Currency(String currency) {
        this.currency = currency;
    }
}