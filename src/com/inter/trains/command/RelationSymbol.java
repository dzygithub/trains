package com.inter.trains.command;

/**
 * only support eq, lt, le
 * eq  indicate ==
 * lt  indicate <
 * le  indicate <=
 * gt  indicate >
 * ge  indicate >=
 */
public enum RelationSymbol {

    EQ("eq"), LT("lt"), LE("le"), GT("gt"), GE("ge");

    private String s;

    private RelationSymbol(String s) {
        this.s = s;
    }

    public static boolean contains(String name){
        try{
            RelationSymbol.valueOf(name.toUpperCase());
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