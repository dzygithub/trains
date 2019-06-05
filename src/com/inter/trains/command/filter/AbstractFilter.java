package com.inter.trains.command.filter;

import com.inter.trains.command.Condition;
import com.inter.trains.command.LogicSymbol;
import com.inter.trains.executor.RouteCounter;


public class AbstractFilter implements Filter {

    private Condition condition;

    Filter nextFilter;

    private RouteCounter routeCounter;

    public RouteCounter getRouteCounter() {
        return routeCounter;
    }

    public void setRouteCounter(RouteCounter routeCounter) {
        this.routeCounter = routeCounter;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }

    public Filter addFilter(Filter filter) {
        this.nextFilter = filter;
        return nextFilter;
    }

    public int doFilter(int preFilterResult) {
        if (this.nextFilter == null) {
            return preFilterResult;
        }
        if (condition.getLogicSymbol() == LogicSymbol.AND) {
            if (preFilterResult == 1) {
                return this.nextFilter.doFilter(preFilterResult);
            }
        } else if (condition.getLogicSymbol() == LogicSymbol.OR) {
            if (preFilterResult == -1) {
                return this.nextFilter.doFilter(preFilterResult);
            }
        }
        return preFilterResult;
    }
}
