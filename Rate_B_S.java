package com.yazapps.mepowerbills;

public class Rate_B_S {

    // "Enetoku Season plus B";

    // MUST BE 30A - 60A
    static final char TYPE = 'B';
    static final char SIZE = 'S';
    static double unit;
    static final double BASE_RATE = 345.60;
    static final double FIXED_RATE = 4551.12;
    static final double METERED_RATE = 28.84;
    static final double WINTER_FIXED = 5302.80;
    static final double WINTER_METERED = 34.24;
    static final double MAX = 200.0;
    static final double AC_DISCOUNT = 300.0;

    static double getUnit(MyContract contract) {

        switch (contract.getMyUnit()) {

            case "30A":
                return 3.0;

            case "40A":
                return 4.0;

            case "50A":
                return 5.0;

            case "60A":
                return 6.0;

            default:
                return 0.0;
        }
    }
}
