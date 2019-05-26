package com.inter.trains.model;

import java.util.HashMap;
import java.util.List;

/**
 * stations graph map
 */
public class GraphModel {

    private HashMap<String, StationNode> stationGraphMap;

    public GraphModel() {
        this.stationGraphMap = new HashMap<String, StationNode>();
    }

    public HashMap<String, StationNode> getStationGraphMap() {
        return this.stationGraphMap;
    }

    /**
     * add route into graph map
     * @param startStationName  start station name
     * @param endStationName    end station name
     * @param distance          distance between two stations
     * @throws Exception
     */
    public void add2Graph(String startStationName, String endStationName, int distance) throws Exception {

        StationNode startStationNode = (StationNode) stationGraphMap.get(startStationName);
        if (startStationNode == null) {
            startStationNode = new StationNode(startStationName);
            stationGraphMap.put(startStationName, startStationNode);
        }
        StationNode endStationNode = (StationNode) stationGraphMap.get(endStationName);
        if (endStationNode == null) {
            endStationNode = new StationNode(endStationName);
            stationGraphMap.put(endStationName, endStationNode);
        }
        startStationNode.addOutStationRoute(new StationRoute(endStationNode, distance));
        endStationNode.addInStationRoute(new StationRoute(startStationNode, distance));
    }

    public void addRoutes(List<String> routes) throws Exception {
        for(String s:routes){
            this.addRoute(s);
        }
    }

    public void addRoute(String routeStr) throws Exception {
        String startStationName = String.valueOf(routeStr.charAt(0));
        String endStationName = String.valueOf(routeStr.charAt(1));
        int distance = Character.getNumericValue(routeStr.charAt(2));
        this.add2Graph(startStationName, endStationName, distance);
    }
}


