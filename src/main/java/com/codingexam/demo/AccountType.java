package com.codingexam.demo;

public enum AccountType {

    S("Savings"),

    C("Checking");

    private String description;

    AccountType(final String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}
