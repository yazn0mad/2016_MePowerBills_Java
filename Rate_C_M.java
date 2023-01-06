package com.yazapps.mepowerbills;

import static java.lang.Double.parseDouble;

public class Rate_C_M {

    // "Enetoku M plan C"


    private MyContract contract;
    static final char TYPE = 'C';
    static final char SIZE = 'M';
    static double unit; // kVA input
    static final double BASE_RATE = 334.80;
    static final double FIXED_RATE = 5940.00;
    static final double METERED_RATE= 30.69;
    static final double MAX = 250.0;

    static double getUnit(MyContract contract) {

        return parseDouble(contract.getMyUnit());
    }
}
