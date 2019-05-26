package com.inter.trains.model;

import java.util.ArrayList;
import java.util.List;

/**
 * a wrapper for StationRoute
 * can be easy check if there has a route for a certain station to another station
 *
 */
public class StationRoutesWrap {

    private List<StationRoute> stationRouteList;

    private List<String> containStationNameList;

    public List<StationRoute> getStationRouteList() {
        return stationRouteList;
    }

    public void setStationRouteList(List<StationRoute> stationRouteList) {
        this.stationRouteList = stationRouteList;
    }

    public StationRoutesWrap() {
        this.stationRouteList = new ArrayList<StationRoute>();
        this.containStationNameList = new ArrayList<String>();
    }

    public boolean add(StationRoute stationRoute) {
        containStationNameList.add(stationRoute.getStationNode().getName());
        return this.stationRouteList.add(stationRoute);
    }

    public boolean hasStationNode(StationNode stationNode) {
        return this.containStationNameList.contains(stationNode.getName());
    }

    public StationRoute getStationRoute(String stationNodeName) {
        StationRoute stationRoute = null;
        for (StationRoute tmpStationRoute : this.stationRouteList) {
            String tmpStationNodeName = tmpStationRoute.getStationNode().getName();
            if (stationNodeName.equals(tmpStationNodeName)) {
                stationRoute = tmpStationRoute;
                break;
            }
        }
        return stationRoute;
    }

}
