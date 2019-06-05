package com.inter.trains.executor;


import java.util.ArrayList;
import java.util.List;

public class RouteCounter {

    // the station list need to be go through
    private List<String> stationNodeNameList;

    // total distance
    private Integer totalDistance;

    // total stops
    private Integer totalStops;

    public RouteCounter() {
        this.stationNodeNameList = new ArrayList<String>();
        this.totalDistance = 0;
        this.totalStops = 0;
    }

    public List<String> getStationNodeNameList() {
        return stationNodeNameList;
    }

    public void setStationNodeNameList(List<String> stationNodeNameList) {
        this.stationNodeNameList = stationNodeNameList;
    }

    public Integer getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(Integer totalDistance) {
        this.totalDistance = totalDistance;
    }

    public Integer getTotalStops() {
        return totalStops;
    }

    public void setTotalStops(Integer totalStops) {
        this.totalStops = totalStops;
    }

}
