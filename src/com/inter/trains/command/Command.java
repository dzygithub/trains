package com.inter.trains.command;

import java.util.ArrayList;
import java.util.List;

/**
 * parse the client command string. extract the conditions, starting station and terminal station
 */
public class Command {

    private String originalCommand;
    private String executorName;
    private String[] routes;
    private List<Condition> conditionList;

    public Command(String originalCommand) throws Exception {
        this.originalCommand = originalCommand;
        List<Object> parseStrList = this.parseCommand(this.originalCommand);
        this.executorName = (String) parseStrList.get(0);
        this.routes = (String[]) parseStrList.get(1);
        this.conditionList = (List<Condition>) parseStrList.get(2);
    }

    public String[] getRoutes() {
        return routes;
    }

    public void setRoutes(String[] routes) {
        this.routes = routes;
    }

    public List<Condition> getConditionList() {
        return this.conditionList;
    }

    public void setConditionList(List<Condition> conditionList) {
        this.conditionList = conditionList;
    }

    public String getExecutorName() {
        return this.executorName;
    }

    public void setExecutorName(String executorName) {
        this.executorName = executorName;
    }

    public String getOriginalCommand() {
        return originalCommand;
    }

    public void setOriginalCommand(String originalCommand) {
        this.originalCommand = originalCommand;
    }


    private List<Object> parseCommand(String command) throws Exception {
        List<Object> cList = new ArrayList<Object>();
        String[] comms = command.split("\\?");
        cList.add(comms[0].trim());
        if (comms.length > 1) {
            String[] routesAndFilters = comms[1].split(":");
            String[] routes = routesAndFilters[0].trim().split("-");
            cList.add(routes);
            if (routesAndFilters.length > 1) {
                String[] filters = routesAndFilters[1].trim().split("&&");
                cList.add(this.parseCondition(filters));
            } else {
                cList.add(new ArrayList<Condition>());
            }
        }
        return cList;

    }

    private List<Condition> parseCondition(String[] filters) throws Exception {
        ArrayList<Condition> rsList = new ArrayList<Condition>();
        for (String filterStr : filters) {
            String[] ss = filterStr.split("\\s+");
            if (ss.length == 0) {
                continue;
            }
            if (ss.length != 3) {
                throw new Exception("condition format is wrong.");
            }
            String keyWord = ss[0];
            if (!KeyWord.contains(keyWord)) {
                throw new Exception(String.format("keyword of %s is not supported.", keyWord));
            }
            String symbol = ss[1];
            if (!OperSymbol.contains(symbol)) {
                throw new Exception(String.format("operSymbol of %s is not supported.", symbol));
            }
            String value = ss[2];
            try {
                Integer.parseInt(value);
            } catch (Exception e) {
                throw new Exception(String.format("value must be number, you provide is %s.", value));
            }
            Condition condition = new Condition(
                    KeyWord.valueOf(keyWord.toUpperCase()),
                    OperSymbol.valueOf(symbol.toUpperCase()),
                    Integer.parseInt(value));
            rsList.add(condition);
        }
        return rsList;
    }
}
