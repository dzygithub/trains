package com.inter.trains.command;

public enum LogicSymbol {

    AND("and"), OR("or");

    private String s;

    private LogicSymbol(String s) {
        this.s = s;
    }

    public static boolean contains(String name){
        try{
            LogicSymbol.valueOf(name.toUpperCase());
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
