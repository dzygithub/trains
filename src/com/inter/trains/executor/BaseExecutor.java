package com.inter.trains.executor;

import com.inter.trains.command.Command;
import com.inter.trains.command.Condition;
import com.inter.trains.command.ConditionsFilter;
import com.inter.trains.command.LogicSymbol;
import com.inter.trains.command.filter.Filter;
import com.inter.trains.model.GraphModel;
import com.inter.trains.model.StationNode;
import com.inter.trains.model.StationRoutesWrap;

import java.util.*;


public abstract class BaseExecutor {

    private GraphModel graphModel;

    private Command command;

    private List<RouteCounter> availableRouteCounterList;

    public BaseExecutor() {
        this.availableRouteCounterList = new ArrayList<RouteCounter>();
    }

    public List<RouteCounter> getAvailableRouteCounterList() {
        return availableRouteCounterList;
    }

    public void setAvailableRouteCounterList(List<RouteCounter> availableRouteCounterList) {
        this.availableRouteCounterList = availableRouteCounterList;
    }

    public GraphModel getGraphModel() {
        return graphModel;
    }

    public void setGraphModel(GraphModel graphModel) {
        this.graphModel = graphModel;
    }

    public Command getCommand() {
        return command;
    }

    public void setCommand(Command command) throws Exception {
        this.command = command;
    }

    public String[] getCommandRoutes() throws Exception {
        Map<String, StationNode> stationGraphMap = this.getStationGraphMap();
        String[] routeNames = this.getCommand().getRoutes();
        for (String name : routeNames) {
            if (stationGraphMap.get(name) == null) {
                throw new Exception("NO SUCH ROUTE");
            }
        }
        return routeNames;
    }

    public HashMap<String, StationNode> getStationGraphMap() throws Exception {
        HashMap<String, StationNode> stationGraphMap = this.getGraphModel().getStationGraphMap();
        if (stationGraphMap == null || stationGraphMap.isEmpty()) {
            throw new Exception("No data in Graph.");
        }
        return stationGraphMap;
    }

    /**
     * Calculate the distance of the two station. if there is no route. then raise exception of "NO SUCH ROUTE"
     * Note:
     * two stations must be directly connection, not other stops
     **/
    public int getDistance(StationNode startStationNode, StationNode nextStationNode) throws Exception {
        int distance = 0;
        if (startStationNode == null || nextStationNode == null) {
            throw new Exception("NO SUCH ROUTE");
        }
        StationRoutesWrap outStationRoutesWrap = startStationNode.getOutStationRoutesWrap();
        if (outStationRoutesWrap.hasStationNode(nextStationNode)) {
            distance = outStationRoutesWrap.getStationRoute(nextStationNode.getName()).getEdgeDistance();
        }
        if (distance == 0) {
            throw new Exception("NO SUCH ROUTE");
        } else {
            return distance;
        }
    }

    public RouteCounter extendRouteCounter(RouteCounter counterT, String stationNodeName, Integer distance) {
        RouteCounter routeCounter = new RouteCounter();
        counterT.getStationNodeNameList().stream().forEach(item -> routeCounter.getStationNodeNameList().add(item));
        routeCounter.getStationNodeNameList().add(stationNodeName);
        Integer tmpDistance = counterT.getTotalDistance();
        Integer tmpStop = counterT.getTotalStops();
        routeCounter.setTotalDistance(tmpDistance + distance);
        routeCounter.setTotalStops(tmpStop + 1);
        return routeCounter;
    }

    /*
     *  return :
     *          1   match to request condition
     *          0   pending, need to execute further.
     *         -1   not match to request condition
     */
    public int filterCondition(RouteCounter routeCounter) {
        int filterResult = 1;
        Filter filter = null, nextFilter = null;
        List<Condition> conditionList = this.getCommand().getConditionList();
        conditionList.sort((e2, e1) -> (e2.getLogicSymbol() == LogicSymbol.OR) ? -1 : 0);
        for (Condition condition : conditionList) {
            if(filter == null){
                filter = nextFilter = ConditionsFilter.createFilter(condition, routeCounter);
            }else{
                nextFilter = nextFilter.addFilter(ConditionsFilter.createFilter(condition, routeCounter));
            }
        }
        if (filter != null) {
            filterResult = filter.doFilter(1);
        }
        return filterResult;
    }
}

