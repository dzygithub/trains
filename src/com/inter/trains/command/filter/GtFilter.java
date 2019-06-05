package com.inter.trains.command.filter;

import com.inter.trains.command.Condition;
import com.inter.trains.command.KeyWord;
import com.inter.trains.executor.RouteCounter;

public class GtFilter extends AbstractFilter {

    public GtFilter(Condition condition, RouteCounter routeCounter) {
        this.setCondition(condition);
        this.setRouteCounter(routeCounter);
    }

    @Override
    public int doFilter(int preFilterResult) {
        int compareR = preFilterResult;
        Condition condition = this.getCondition();
        RouteCounter routeCounter = this.getRouteCounter();
        Integer conditionValue = condition.getValue();
        KeyWord kw = condition.getKeyWord();
        Integer compareV = (kw == KeyWord.DISTANCE) ? routeCounter.getTotalDistance() : routeCounter.getTotalStops();
        boolean eqBool = compareV <= conditionValue;
        if (eqBool) {
            compareR = 0;
        } else {
            compareR = (compareV > conditionValue) ? 1 : -1;
        }
        return super.doFilter(compareR);
    }
}
