package test.com.inter.trains.command;

import com.inter.trains.command.*;
import com.inter.trains.command.filter.Filter;
import com.inter.trains.executor.RouteCounter;
import org.junit.Test;

import static org.junit.Assert.*;

public class ConditionsFilterTest {

    @Test
    public void createFilter() {
        RouteCounter routeCounter = new RouteCounter();
        Condition condition = new Condition(LogicSymbol.AND, KeyWord.STOPS, RelationSymbol.GT, 3);
        Filter filter = ConditionsFilter.createFilter(condition, routeCounter);
        assertEquals("GtFilter", filter.getClass().getSimpleName());

        condition = new Condition(LogicSymbol.AND, KeyWord.STOPS, RelationSymbol.LT, 3);
        filter = ConditionsFilter.createFilter(condition, routeCounter);
        assertEquals("LtFilter", filter.getClass().getSimpleName());

        condition = new Condition(LogicSymbol.AND, KeyWord.STOPS, RelationSymbol.GE, 3);
        filter = ConditionsFilter.createFilter(condition, routeCounter);
        assertEquals("GeFilter", filter.getClass().getSimpleName());

        condition = new Condition(LogicSymbol.AND, KeyWord.STOPS, RelationSymbol.LE, 3);
        filter = ConditionsFilter.createFilter(condition, routeCounter);
        assertEquals("LeFilter", filter.getClass().getSimpleName());

        condition = new Condition(LogicSymbol.AND, KeyWord.STOPS, RelationSymbol.EQ, 3);
        filter = ConditionsFilter.createFilter(condition, routeCounter);
        assertEquals("EqFilter", filter.getClass().getSimpleName());
    }
}