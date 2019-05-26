package com.inter.trains.command;

/**
 * only support stops, distance
 */
public enum KeyWord {

    STOPS("stops"), DISTANCE("distance");

    private String s;

    private KeyWord(String s) {
        this.s = s;
    }

    public static boolean contains(String name){
        try{
            KeyWord.valueOf(name.toUpperCase());
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
