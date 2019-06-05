package com.inter.trains.command.filter;

import com.inter.trains.command.Condition;
import com.inter.trains.command.KeyWord;
import com.inter.trains.executor.RouteCounter;

public class LtFilter extends AbstractFilter {

    public LtFilter(Condition condition, RouteCounter routeCounter) {
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
        compareR = (compareV < conditionValue) ? 1 : -1;
        return super.doFilter(compareR);
    }
}
