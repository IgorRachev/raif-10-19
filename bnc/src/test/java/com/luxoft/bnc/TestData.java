package com.luxoft.bnc;

public enum TestData {

    data1(1423, 1, "8654"),
    data2(6875, 3, "8654"),
    data3(1289, 1, "8654");


    private int attempt;



    private int expectedCows;



    private String hidden;


    //constructor
    TestData(int attempt, int expectedCows, String hidden) {
        this.attempt = attempt;
        this.expectedCows = expectedCows;
        this.hidden = hidden;
    }

    //getters
    public int getAttempt() {
        return attempt;
    }

    public int getExpectedCows() {
        return expectedCows;
    }

    public String getHidden() {
        return hidden;
    }
}
