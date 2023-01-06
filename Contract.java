package com.yazapps.mepowerbills;

// These are the contract types

public class Contract {

    private int pos;    // position of "contract_list" string-array in xml
    private char type;
    private char size;
    private boolean isWebPlus;

    // TYPE: B, C, H(HEATING)
    // SIZE: L, M, S(SEASON), W(WEB), X(LEGACY)

    Contract(int pos, char type, char size, boolean isWebPlus) {
        this.pos = pos;
        this.type = type;
        this.size = size;
        this.isWebPlus = isWebPlus;
    }

    static final Contract[] contracts = {
            new Contract(0,'B', 'L', false),
            new Contract(1,'C', 'M', false),
            new Contract(2,'B', 'M', false),
            new Contract(3, 'C', 'M', false),
            new Contract(4,  'B', 'S', false),
            new Contract(5,  'C', 'S', false),
            new Contract(6,  'B', 'W', true),
            new Contract(7, 'C', 'W', true),
            new Contract(8,  'B', 'W', false),
            new Contract(9,  'C', 'W', false),
            new Contract(10,  'H', 'X', false)
    };

    public int getPos() {
        return pos;
    }

    public char getType() {
        return type;
    }

    public char getSize() { return  size; }

    boolean isWebPlus() {
        return isWebPlus;
    }

}
