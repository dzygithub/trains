package com.inter.trains.executor;

import com.inter.trains.model.StationNode;
import com.inter.trains.model.StationRoute;

import java.util.List;
import java.util.Map;


/**
 * Calculate the shortest distance from one station to another station in term of command & conditions .
 * EG:
 * The length of the shortest route (in terms of distance to travel) from B to B
 */
public class ShortestDistanceExecutor extends BaseExecutor implements Executor<Integer> {


    /*
     *  "slowPointer fastPointer loopDepth" are defined for check and avoid the infinite loop
     *
     */
    private String slowPointer, fastPointer;

    private Integer loopDepth = 0;

    public ShortestDistanceExecutor() {
        this.slowPointer = this.fastPointer = "";
    }


    @Override
    public Integer execute() throws Exception {
        Map<String, StationNode> stationGraphMap = this.getStationGraphMap();
        String[] routeNames = this.getCommandRoutes();
        String startStationName = routeNames[0];
        StationNode startStationNode = stationGraphMap.get(startStationName);
        this.searchShortest(startStationNode, null);
        if (this.getAvailableRouteCounterList().size() > 0) {
            return this.getAvailableRouteCounterList().get(0).getTotalDistance();
        } else {
            throw new Exception("NO SUCH ROUTE");
        }
    }

    /**
     * DFS , recursive to search the shortest route
     *
     * @param startStationNode
     * @param routeCounter
     */
    public void searchShortest(StationNode startStationNode, RouteCounter routeCounter) {
        if (routeCounter == null) {
            routeCounter = new RouteCounter();
            routeCounter.getStationNodeNameList().add(startStationNode.getName());
        }
        List<StationRoute> stationRouteList = startStationNode.getOutStationRoutesWrap().getStationRouteList();
        for (StationRoute stationRoute : stationRouteList) {
            this.search(startStationNode, stationRoute, routeCounter);
            this.loopDepth = 0;
        }
    }

    public void search(StationNode startStationNode, StationRoute stationRoute, RouteCounter routeCounter) {
        RouteCounter rc = this.extendRouteCounter(routeCounter,
                stationRoute.getStationNode().getName(),
                stationRoute.getEdgeDistance());
        String terminalStationName = this.getCommand().getRoutes()[1];
        List<RouteCounter> availableRouteCounterList = this.getAvailableRouteCounterList();
        if (stationRoute.getStationNode().getName().equals(terminalStationName)) {
            if (availableRouteCounterList.size() == 0 || rc.getTotalDistance() <
                    availableRouteCounterList.get(0).getTotalDistance()) {
                availableRouteCounterList.add(0, rc);
            }
            return;
        }
        /**
         * check if there has infinite loop, if it is, then do not need to deep search again. back to parent node.
         */
        this.loopDepth += 1;
        if (this.loopDepth % 2 == 1) {
            this.slowPointer = startStationNode.getName();
        } else {
            this.fastPointer = stationRoute.getStationNode().getName();
        }
        if (this.fastPointer.equals(this.slowPointer)) {
            //means there has a infinite loop here
            return;
        }
        this.searchShortest(stationRoute.getStationNode(), rc);
    }
}
