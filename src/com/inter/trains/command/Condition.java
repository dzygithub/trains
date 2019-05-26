package com.inter.trains.command;


/**
 * condition class for command
 * EG:
 *    stops le 3
 */
public class Condition {

    KeyWord keyWord;
    OperSymbol operSymbol;
    int value;

    public Condition(KeyWord keyWord, OperSymbol operSymbol, int value) {
        this.keyWord = keyWord;
        this.operSymbol = operSymbol;
        this.value = value;
    }

    public Condition(KeyWord keyWord, int value) {
        this.keyWord = keyWord;
        this.value = value;
    }

    public KeyWord getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(KeyWord keyWord) {
        this.keyWord = keyWord;
    }

    public OperSymbol getOperSymbol() {
        return operSymbol;
    }

    public void setOperSymbol(OperSymbol operSymbol) {
        this.operSymbol = operSymbol;
    }

    public int getValue() {
        return value;
    }

    public void valuePlus(int v){
        this.value += v;
    }

    public void setValue(int value) {
        this.value = value;
    }


    public static void main(String[] args) {

        String symbolStr = "le";
        boolean symbol = OperSymbol.contains(symbolStr);
        System.out.println(symbol);
        System.out.println(OperSymbol.LE == OperSymbol.valueOf(symbolStr.toUpperCase()));
    }
}
