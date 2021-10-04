package com.example.psychometrictest.TEST;

public class TestModel {
    private  String testid;
    private  int Topscore;
    private int time;

    public String getTestid() {
        return testid;
    }

    public void setTestid(String testid) {
        this.testid = testid;
    }

    public int getTopscore() {
        return Topscore;
    }

    public void setTopscore(int topscore) {
        Topscore = topscore;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }



    public TestModel(String testid, int topscore, int time) {
        this.testid = testid;
        Topscore = topscore;
        this.time = time;
    }


}
