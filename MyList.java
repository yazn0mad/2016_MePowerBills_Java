package com.yazapps.mepowerbills;

// singleton BNR 159 CrimeLab

import android.content.Context;

import java.util.ArrayList;
import java.util.List;

public class MyList {

    private static MyList sMyContract;

    private List<MyContract> myListItems;
    //public static List<String> myContractNames = new ArrayList<>();
    public static List<MyContract> mySelections = new ArrayList<>();

    // may not need this, for SQL database for saving
    public static MyList get(Context context) {
        if (sMyContract == null) {
            sMyContract = new MyList(context);
        }
        return  sMyContract;
    }

    // i<12 = num of contract types. pg161 BNR
    private MyList(Context context) {
        myListItems = new ArrayList<>();
        for (int i=0; i<mySelections.size(); i++) {
            //Contract contract = new Contract();
            //contract.setName(contract.getName());
            //mMyContracts.add(contract);
        }
    }

    public List<MyContract> getContracts() {
       // return mMyContracts;
        return mySelections;
    }

    public static String getContractName(List<MyContract> list) {
        String name = "unknown";
        for (MyContract contract : list) {
            name = contract.getName();
            return name;
        }
        return name;
    }

}
