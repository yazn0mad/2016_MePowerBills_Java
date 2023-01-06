package com.yazapps.mepowerbills;

public class Rate_B_L {

    // Enetoku L Plan B

    static final char TYPE = 'B';
    static final char SIZE = 'L';
    static double unit;
    static final double BASE_RATE = 334.80;
    static final double FIXED_RATE = 10970.00;
    static final double METERED_RATE = 31.69;
    static final double MAX = 400.0;

    static double getUnit(MyContract contract) {

        switch (contract.getMyUnit()) {
            case "10A":
                return 1.0;

            case "15A":
                return 1.5;

            case "20A":
                return 2.0;

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
