package com.yazapps.mepowerbills;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static java.lang.Double.parseDouble;

public class RateCalculator {

    static Date startDate;
    static Date endDate;
    static Date currentDate;

    // Variable for Size 'L' & 'M'
    static double unit;
    static double baseRate;
    static double fixedRate;
    static double meteredRate;
    static double max;

    // Variables for Size 'W'
    static double lowRate;
    static double midRate;
    static double highRate;
    static double lowPack;
    static double midPack;
    static double webDiscount;

    // Variables for Size 'S'
    static double winterFixed;
    static double winterMetered;
    static double acDiscount;
    static boolean isWinter;

    // Variables for Type 'H'
    static double minPeriodBase;
    static double offPeriodBase;
    static int minPeriodStartMonth;
    static int powerFactorPosition;
    static double powerFactor;

    private static void getValues(MyContract contract) {

        char type = contract.getType();
        char size = contract.getSize();

        if (type == 'B') {

            switch (size) {

                case 'M':
                    unit = Rate_B_M.getUnit(contract);
                    baseRate = Rate_B_M.BASE_RATE;
                    fixedRate = Rate_B_M.FIXED_RATE;
                    meteredRate = Rate_B_M.METERED_RATE;
                    max = Rate_B_M.MAX;
                    break;

                case 'L':
                    unit = Rate_B_L.getUnit(contract);
                    baseRate = Rate_B_L.BASE_RATE;
                    fixedRate = Rate_B_L.FIXED_RATE;
                    meteredRate = Rate_B_L.METERED_RATE;
                    max = Rate_B_L.MAX;
                    break;

                case 'W':
                    unit = Rate_B_W.getUnit(contract);
                    baseRate = Rate_B_W.BASE_RATE;
                    lowRate = Rate_B_W.LOW_RATE;
                    midRate = Rate_B_W.MID_RATE;
                    highRate = Rate_B_W.HIGH_RATE;
                    lowPack = Rate_B_W.LOW_PACK;
                    midPack = Rate_B_W.MID_PACK;
                    webDiscount = Rate_B_W.WEB_DISCOUNT;
                    break;

                case 'S':
                    unit = Rate_B_S.getUnit(contract);
                    baseRate = Rate_B_S.BASE_RATE;
                    fixedRate = Rate_B_S.FIXED_RATE;
                    meteredRate = Rate_B_S.METERED_RATE;
                    winterFixed = Rate_B_S.WINTER_FIXED;
                    winterMetered = Rate_B_S.WINTER_METERED;
                    max = Rate_B_S.MAX;
                    acDiscount = Rate_B_S.AC_DISCOUNT;
            }
        }

        if (type == 'C') {
            switch (size) {
                case 'M':
                    unit = Rate_C_M.getUnit(contract); // CHANGE LATER
                    baseRate = Rate_C_M.BASE_RATE;
                    fixedRate = Rate_C_M.FIXED_RATE;
                    meteredRate = Rate_C_M.METERED_RATE;
                    max = Rate_C_M.MAX;
                    break;

                case 'L':
                    unit = Rate_C_L.getUnit(contract); // FIX LATER
                    baseRate = Rate_C_L.BASE_RATE;
                    fixedRate = Rate_C_L.FIXED_RATE;
                    meteredRate = Rate_C_L.METERED_RATE;
                    max = Rate_C_L.MAX;
                    break;

                case 'W':
                    unit = Rate_C_W.getUnit(contract);
                    baseRate = Rate_C_W.BASE_RATE;
                    lowRate = Rate_C_W.LOW_RATE;
                    midRate = Rate_C_W.MID_RATE;
                    highRate = Rate_C_W.HIGH_RATE;
                    lowPack = Rate_C_W.LOW_PACK;
                    midPack = Rate_C_W.MID_PACK;
                    webDiscount = Rate_C_W.WEB_DISCOUNT;
                    break;

                case 'S':
                    unit = Rate_C_S.getUnit(contract);
                    baseRate = Rate_C_S.BASE_RATE;
                    fixedRate = Rate_C_S.FIXED_RATE;
                    meteredRate = Rate_C_S.METERED_RATE;
                    winterFixed = Rate_C_S.WINTER_FIXED;
                    winterMetered = Rate_C_S.WINTER_METERED;
                    max = Rate_C_S.MAX;
                    acDiscount = Rate_C_S.AC_DISCOUNT;
            }
        }

        if (type == 'H') {

            unit = Rate_H_L.getUnit(contract);
            minPeriodBase = Rate_H_L.MIN_PERIOD_BASE;
            offPeriodBase = Rate_H_L.OFF_PERIOD_BASE;
            meteredRate = Rate_H_L.METERED_RATE;
            minPeriodStartMonth = DetailFragment_2.minMonth;
            powerFactorPosition = DetailFragment_2.powerPosition;

        }
    }

    static int computeBalance(MyContract contract) {

        char size = contract.getSize();

        getValues(contract);

        double totalBase = getTotalBaseRate();
        double totalKwh = getTotalKwh(contract);
        double discount = -(StaticData.discountRate * totalKwh);
        int levy = (int) (StaticData.levyRate * totalKwh);
        int total;

        switch (size) {

            case 'W':
                boolean isWebPlus = contract.isWebPlus();
                if (totalKwh >= 281) {
                    total = (int) (((totalKwh - 280) * highRate + midPack + lowPack) +
                                totalBase + discount);
                } else if (totalKwh >= 121) {
                    total = (int) (((totalKwh - 120) * midRate + lowPack) + totalBase + discount);
                } else {
                    total = (int) ((totalKwh * lowRate) + totalBase + discount);
                }
                if (!isWebPlus) {
                    return total + levy;
                } else {
                    return total + levy - (int) webDiscount;
                }

            case 'S':
                int myMonth = StaticData.getMonth(contract.getMyDate());
                isWinter = StaticData.checkWinterMonths(myMonth);
                if (isWinter) {
                    fixedRate = winterFixed;
                    meteredRate = winterMetered;
                }
                if (totalKwh <= max) {
                    total = (int) (fixedRate + totalBase + discount);
                } else {
                    total = (int) (fixedRate + totalBase + ((totalKwh - max) * meteredRate) + discount);
                }
                return total + levy - (int) acDiscount;

            case 'X':
                double myBaseRate;
                powerFactor = Rate_H_L.getPowerFactor(powerFactorPosition);
                //int minMonth = StaticData.getMonth(contract.getMyDate());
                String month = Integer.toString(StaticData.getMonth(contract.getMyDate()));
                List<String > list = Arrays.asList(Rate_H_L.setMinPeriod(minPeriodStartMonth));
                if (list.contains(month)) {
                    myBaseRate = unit * minPeriodBase * powerFactor;
                } else {
                    myBaseRate = unit * offPeriodBase * powerFactor;
                }
                total = (int) (myBaseRate + totalKwh * meteredRate + discount);
                return total + levy;

            default:    // SIZE = 'L'
                if (totalKwh <= max) {
                    total = (int) (fixedRate + totalBase + discount);
                } else {
                    total = (int) (fixedRate + totalBase + ((totalKwh - max) * meteredRate) + discount);
                }
                return total + levy;
        }

    }

    private static double getTotalBaseRate() {

        return unit * baseRate;
    }

    static double getTotalKwh(MyContract contract) {

        double startMeter = parseDouble(contract.getMyStartMeter());
        double currentMeter = parseDouble(contract.getMyCurrentMeter());

        return currentMeter - startMeter;
    }
}

