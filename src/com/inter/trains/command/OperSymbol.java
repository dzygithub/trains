package com.inter.trains.command;

/**
 * only support eq, lt, le
 * eq  indicate ==
 * lt  indicate <
 * le  indicate <=
 */
public enum OperSymbol {

    EQ("eq"), LT("lt"), LE("le");

    private String s;

    private OperSymbol(String s) {
        this.s = s;
    }

    public static boolean contains(String name){
        try{
            OperSymbol.valueOf(name.toUpperCase());
            return true;
        }catch (Exception e){
            return false;
        }
    }


    @Override
    public String toString(){
        return this.s;
    }
}