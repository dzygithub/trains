package com.inter.trains.command;


/**
 * condition class for command
 * EG:
 * stops le 3
 */
public class Condition {

    private KeyWord keyWord;

    private RelationSymbol relationSymbol;

    private LogicSymbol logicSymbol = LogicSymbol.AND;

    private int value;

    public Condition(LogicSymbol logicSymbol, KeyWord keyWord, RelationSymbol relationSymbol, int value) {
        this.keyWord = keyWord;
        this.relationSymbol = relationSymbol;
        this.value = value;
        this.logicSymbol = logicSymbol;
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

    public RelationSymbol getRelationSymbol() {
        return relationSymbol;
    }

    public void setRelationSymbol(RelationSymbol relationSymbol) {
        this.relationSymbol = relationSymbol;
    }

    public int getValue() {
        return value;
    }

    public void valuePlus(int v) {
        this.value += v;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public LogicSymbol getLogicSymbol() {
        return logicSymbol;
    }

    public void setLogicSymbol(LogicSymbol logicSymbol) {
        this.logicSymbol = logicSymbol;
    }

    public static void main(String[] args) {

        String symbolStr = "le";
        boolean symbol = RelationSymbol.contains(symbolStr);
        System.out.println(symbol);
        System.out.println(RelationSymbol.LE == RelationSymbol.valueOf(symbolStr.toUpperCase()));
    }
}
