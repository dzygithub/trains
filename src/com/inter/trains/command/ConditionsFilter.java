package com.inter.trains.command;

import com.inter.trains.command.filter.*;
import com.inter.trains.executor.RouteCounter;

public class ConditionsFilter {


    public static Filter createFilter(Condition condition, RouteCounter routeCounter) {
        Filter filter = null;
        RelationSymbol relationSymbol = condition.getRelationSymbol();
        if (relationSymbol == RelationSymbol.EQ) {
            filter = new EqFilter(condition, routeCounter);
        } else if (relationSymbol == RelationSymbol.LE) {
            filter = new LeFilter(condition, routeCounter);
        } else if (relationSymbol == RelationSymbol.GE) {
            filter = new GeFilter(condition, routeCounter);
        } else if (relationSymbol == RelationSymbol.GT) {
            filter = new GtFilter(condition, routeCounter);
        } else if (relationSymbol == RelationSymbol.LT) {
            filter = new LtFilter(condition, routeCounter);
        }
        return filter;
    }
}
