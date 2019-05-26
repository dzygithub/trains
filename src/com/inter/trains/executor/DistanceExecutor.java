package com.inter.trains.executor;

import com.inter.trains.model.StationNode;

import java.util.*;


/**
 * Calculate the total distance for one route in term of command & conditions .
 * EG:
 * The distance of the route A-B-C
 */
public class DistanceExecutor extends BaseExecutor implements Executor<Integer> {


    public DistanceExecutor() {
        super();
    }

    @Override
    public Integer execute() throws Exception {
        int distance = 0;
        Map<String, StationNode> stationGraphMap = this.getStationGraphMap();
        String[] routeNames = this.getCommandRoutes();
        for (int i = 0; i < routeNames.length - 1; i++) {
            StationNode startStationNode = stationGraphMap.get(routeNames[i]);
            StationNode endStationNode = stationGraphMap.get(routeNames[i + 1]);
            distance += this.getDistance(startStationNode, endStationNode);
        }
        return distance;
    }

}
