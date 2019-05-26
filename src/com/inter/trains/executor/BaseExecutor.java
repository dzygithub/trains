package com.inter.trains.executor;

import com.inter.trains.command.Command;
import com.inter.trains.command.Condition;
import com.inter.trains.command.KeyWord;
import com.inter.trains.command.OperSymbol;
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
        for (String name: routeNames) {
            if (stationGraphMap.get(name) == null) {
                throw new Exception("NO SUCH ROUTE");
            }
        }
        return routeNames;
    }

    public HashMap<String, StationNode> getStationGraphMap() throws Exception {
        HashMap<String, StationNode> stationGraphMap = this.getGraphModel().getStationGraphMap();
        if(stationGraphMap == null || stationGraphMap.isEmpty()){
            throw new Exception("No data in Graph.");
        }
        return stationGraphMap;
    }

    /**
     * Calculate the distance of the two station. if there is no route. then raise exception of "NO SUCH ROUTE"
     * Note:
     *     two stations must be directly connection, not other stops
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


    public int compare(RouteCounter routeCounter, Condition condition) {
        int compareR = 1;
        Integer conditionValue = condition.getValue();
        OperSymbol operSymbol = condition.getOperSymbol();
        KeyWord kw = condition.getKeyWord();
        Integer compareV = (kw == KeyWord.DISTANCE) ? routeCounter.getTotalDistance() : routeCounter.getTotalStops();
        if (operSymbol == OperSymbol.LE) {
            compareR = (compareV <= conditionValue) ? 1 : -1;
        } else if (operSymbol == OperSymbol.LT) {
            compareR = (compareV < conditionValue) ? 1 : -1;
        } else if (operSymbol == OperSymbol.EQ) {
            boolean eqBool = compareV < conditionValue;
            if (eqBool) {
                compareR = 0;
            } else {
                compareR = (compareV == conditionValue) ? 1 : -1;
            }
        }
        return compareR;
    }

}

