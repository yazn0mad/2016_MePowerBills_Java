package com.yazapps.mepowerbills;

import java.util.Date;

public class MyContract extends Contract {

    private String name;
    private String myUnit;
    private Date myDate;
    private String myStartMeter;
    private String myCurrentMeter;
    private String myBalance;
    //private String myAvg;
    private String myKwh;

    MyContract (int pos, char type, char size, boolean web, String name, String unit,
                Date date, String
            startMeter,
                String currentMeter,
                       String balance,
                       String kwh) {

        super(pos, type, size, web);
        this.name = name;
        this.myUnit = unit;
        this.myDate = date;
        this.myStartMeter = startMeter;
        this.myCurrentMeter = currentMeter;
        this.myBalance = balance;
        //this.myAvg = avg;
        this.myKwh = kwh;
    }

    // Instantiate my contract from main contract list
    static MyContract makeMine(int pos, String name) {

        Contract contract = Contract.contracts[pos];
        MyContract myContract = new MyContract(contract.getPos(),
                contract.getType(), contract.getSize(), contract.isWebPlus(), name,"",null,"", "",
                "",
                "");
        return myContract;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMyUnit() {
        return myUnit;
    }

    void setMyUnit(String myUnit) {
        this.myUnit = myUnit;
    }

    Date getMyDate() {
        return myDate;
    }

    void setMyDate(Date myDate) {
        this.myDate = myDate;
    }

    String getMyStartMeter() {
        return myStartMeter;
    }

    void setMyStartMeter(String myMeter) {
        this.myStartMeter = myMeter;
    }

    String getMyCurrentMeter() {
        return myCurrentMeter;
    }

    void setMyCurrentMeter(String myCurrentMeter) {
        this.myCurrentMeter = myCurrentMeter;
    }

    String getMyBalance() {
        return myBalance;
    }

    void setMyBalance(String myBalance) {
        this.myBalance = myBalance;
    }

    String getMyKwh() {
        return myKwh;
    }

    void setMyKwh(String myKwh) {
        this.myKwh = myKwh;
    }

}
