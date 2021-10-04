package com.example.psychometrictest.TEST;

public class skilltest {
    private String name;
    private int noOfTests;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNoOfTests() {
        return noOfTests;
    }

    public void setNoOfTests(int noOfTests) {
        this.noOfTests= noOfTests;
    }

    public skilltest(String name, int noOfTests) {
//        this.docId = docId;
        this.name = name;
        this.noOfTests = noOfTests;
    }
}
