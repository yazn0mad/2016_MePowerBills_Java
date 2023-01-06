package com.yazapps.mepowerbills;

import static java.lang.Double.parseDouble;

public class Rate_H_L {

    // "Hot Time 22 Long"

    private MyContract contract;

    static final char TYPE = 'H';
    static final char SIZE = 'X';
    static double unit; //kW

    // Get these values straight from Detail_Fragment_2 FOR RateCalculator
    static int minPeriodStartMonth;
    static int powerFactorPosition;
    static double powerFactor;
    static final double OVER_85 = 0.95;
    static final double EVEN_85 = 1.0;
    static final double UNDER_85 = 1.05;
    static final double MIN_PERIOD_BASE = 658.80;
    static final double OFF_PERIOD_BASE = 291.60;
    static final double METERED_RATE = 15.40;
    static final int NUM_OF_MONTHS = 6;

    /*
    static int [] setMinPeriod (int startMonth) {
        int [] minPeriod = new int[NUM_OF_MONTHS];
        for (int i = 0; i < NUM_OF_MONTHS; i++) {
            minPeriod[i] = startMonth;
            startMonth++;
        }
        return minPeriod;
    }
    */

    static double getUnit(MyContract contract) {

        return parseDouble(contract.getMyUnit());
    }

    static String[] setMinPeriod (int month) {
        String [] strings = new String [NUM_OF_MONTHS];
        for (int i = 0; i < NUM_OF_MONTHS; i++) {
            if (month > 11) {
                month = month - 12;
            }
            strings[i] = Integer.toString(month);
            month++;
        }
        return strings;
    }

    static double getPowerFactor(int position) {

        switch (position) {

            case 0:
                return OVER_85;

            case 1:
                return EVEN_85;

            case 2:
                return UNDER_85;

                default:
                    return EVEN_85;
        }
    }
}
