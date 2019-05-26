package com.inter.trains.model;

/**
 * station node class
 * include:
 *        station name
 *        go out stations
 *        come in stations
 */
public class StationNode {

    private String name;

    private StationRoutesWrap outStationRoutesWrap;
    private StationRoutesWrap inStationRoutesWrap;

    public StationNode(String name) {
        this.name = name;
        this.outStationRoutesWrap = new StationRoutesWrap();
        this.inStationRoutesWrap = new StationRoutesWrap();
    }

    public void addOutStationRoute(StationRoute outStationNode) {
        this.outStationRoutesWrap.add(outStationNode);
    }

    public void addInStationRoute(StationRoute outStationNode) {
        this.inStationRoutesWrap.add(outStationNode);

    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StationRoutesWrap getOutStationRoutesWrap() {
        return outStationRoutesWrap;
    }

    public void setOutStationRoutesWrap(StationRoutesWrap outStationRoutesWrap) {
        this.outStationRoutesWrap = outStationRoutesWrap;
    }

    public StationRoutesWrap getInStationRoutesWrap() {
        return inStationRoutesWrap;
    }

    public void setInStationRoutesWrap(StationRoutesWrap inStationRoutesWrap) {
        this.inStationRoutesWrap = inStationRoutesWrap;
    }
}
