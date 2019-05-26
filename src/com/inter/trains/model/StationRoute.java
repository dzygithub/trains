package com.inter.trains.model;

/**
 * oriented route information
 * include:
 *         next station node
 *         distance to next station
 */
public class StationRoute {

    private StationNode stationNode;
    private int edgeDistance;

    public StationRoute(StationNode stationNode, int edgeDistance) {
        this.stationNode = stationNode;
        this.edgeDistance = edgeDistance;
    }


    public StationNode getStationNode() {
        return this.stationNode;
    }

    public void setStationNode(StationNode stationNode) {
        this.stationNode = stationNode;
    }

    public int getEdgeDistance() {
        return this.edgeDistance;
    }

    public void setEdgeDistance(int edgeDistance) {
        this.edgeDistance = edgeDistance;
    }
}
