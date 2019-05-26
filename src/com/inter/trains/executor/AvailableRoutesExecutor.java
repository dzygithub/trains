package com.inter.trains.executor;

import com.inter.trains.command.Command;
import com.inter.trains.command.Condition;
import com.inter.trains.model.StationNode;
import com.inter.trains.model.StationRoute;

import java.util.List;
import java.util.Map;


/**
 * Calculate available routes from one station to another station in term of command & conditions .
 * EG:
 * The number of trips starting at C and ending at C with a maximum of 3 stops.
 */
public class AvailableRoutesExecutor extends BaseExecutor implements Executor<Integer> {


    @Override
    public Integer execute() throws Exception {
        Map<String, StationNode> stationGraphMap = this.getStationGraphMap();
        String[] routeNames = this.getCommandRoutes();
        String startStationName = routeNames[0];
        StationNode startStationNode = stationGraphMap.get(startStationName);
        this.searchRoutes(startStationNode, null);
        return this.getAvailableRouteCounterList().size();
    }

    /**
     * DFS , recursive to search the available routes
     * @param startStationNode
     * @param counterT
     */
    public void searchRoutes(StationNode startStationNode, RouteCounter counterT) {
        if (counterT == null) {
            counterT = new RouteCounter();
            counterT.getStationNodeNameList().add(startStationNode.getName());
        }
        List<StationRoute> stationRouteList = startStationNode.getOutStationRoutesWrap().getStationRouteList();
        for (StationRoute stationRoute : stationRouteList) {
            this.search(stationRoute, counterT);
        }
    }

    public void search(StationRoute stationRoute, RouteCounter routeCounter) {
        RouteCounter rc = this.extendRouteCounter(routeCounter,
                stationRoute.getStationNode().getName(),
                stationRoute.getEdgeDistance());
        int isMatch = this.matchCondition(rc);
        if (isMatch < 0) {
            //not match, go back to parent node
            return;
        }
        String terminalStationName = this.getCommand().getRoutes()[1];
        if (isMatch == 1 && stationRoute.getStationNode().getName().equals(terminalStationName)) {
            this.getAvailableRouteCounterList().add(rc);
        }
        this.searchRoutes(stationRoute.getStationNode(), rc);
    }

    /*
     *  return :
     *          1   match to request condition
     *          0   suspending, need to execute further.
     *         -1   not match to request condition
     */
    public int matchCondition(RouteCounter routeCounter) {
        int isMatch = 1;
        List<Condition> conditionList = this.getCommand().getConditionList();
        for (Condition condition : conditionList) {
            isMatch = this.compare(routeCounter, condition);
            if (isMatch != 1) {
                break;
            }
        }
        return isMatch;
    }

    public void setCommand(Command command) throws Exception {
        if (command.getConditionList().size() == 0) {
            throw new Exception("For NumberRoutes Executor, there must be set filter in command.");
        }
        super.setCommand(command);
    }

}
