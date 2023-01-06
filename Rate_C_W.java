package com.yazapps.mepowerbills;

import static java.lang.Double.parseDouble;

public class Rate_C_W {

    // "Lighting C"
    // "Web e Plus C" (isWebPlus = true)

    static final char TYPE = 'C';
    static final char SIZE = 'W';
    static double unit;
    static final double BASE_RATE = 334.80;
    static final double LOW_RATE = 23.54; // Kwh <= 120
    static final double MID_RATE = 29.72; // 121 =< kwh <= 280
    static final double HIGH_RATE= 33.37; // kwh >= 281
    static final double LOW_PACK = 120 * LOW_RATE;
    static final double MID_PACK = 160 * MID_RATE;
    static final double WEB_DISCOUNT = 300.00;

    static double getUnit(MyContract contract) {

        return parseDouble(contract.getMyUnit());
    }
}

// if totalKwh >= 281, ((totalKwh - 280) * HIGH_RATE) + MID_PACK + LOW+PACK
// else if totalKwh >= 121, ((totalKwh - 120) * MID_RATE) + LOW_PACKET
// else totalKwh * LOW_RATE

