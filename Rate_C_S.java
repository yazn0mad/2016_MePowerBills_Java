package com.yazapps.mepowerbills;

import static java.lang.Double.parseDouble;

public class Rate_C_S {

    // "Enetokju Season plus C"

    // MUST BE 7 - 10kVA

    static final char TYPE = 'C';
    static final char SIZE = 'S';
    static double unit;
    static final double BASE_RATE = 345.60;
    static final double FIXED_RATE = 4175.28;
    static final double METERED_RATE = 28.40;
    static final double WINTER_FIXED = 4942.08;
    static final double WINTER_METERED = 33.59;
    static final double MAX = 200.0;
    static final double AC_DISCOUNT = 300.0;

    static double getUnit(MyContract contract) {

        return parseDouble(contract.getMyUnit());
    }
}
