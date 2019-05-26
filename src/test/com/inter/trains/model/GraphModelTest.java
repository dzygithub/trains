package test.com.inter.trains.model;

import com.inter.trains.model.GraphModel;
import com.inter.trains.model.StationNode;
import com.inter.trains.model.StationRoute;
import com.inter.trains.model.StationRoutesWrap;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class GraphModelTest {

    GraphModel graphModel = null;

    @org.junit.Test
    public void addRoute(){
        GraphModel graphModel = new GraphModel();
        try {
            graphModel.addRoute("AB5");
            assertEquals(2, graphModel.getStationGraphMap().size());
            StationNode stationNode = graphModel.getStationGraphMap().get("A");
            assertEquals("A", stationNode.getName());
            StationRoutesWrap wrap = stationNode.getOutStationRoutesWrap();
            List<StationRoute> StationRouteList = wrap.getStationRouteList();
            assertEquals(1, StationRouteList.size());
            assertEquals(5, StationRouteList.get(0).getEdgeDistance());
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals(1, 0);
        }
    }

    @org.junit.Before
    public void init(){
        graphModel = new GraphModel();
        try {
            graphModel.addRoute("AB5");
            graphModel.add2Graph("B", "C", 4);
            graphModel.add2Graph("C", "D", 8);
            graphModel.add2Graph("D", "C", 8);
            graphModel.add2Graph("D", "E", 6);
            graphModel.add2Graph("A", "D", 5);
            graphModel.add2Graph("C", "E", 2);
            graphModel.add2Graph("E", "B", 3);
            graphModel.add2Graph("A", "E", 7);
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals(1, 0);
        }
    }

    @org.junit.Test
    public void getStationGraphMap() {
        Map<String, StationNode> stationGraph = graphModel.getStationGraphMap();
        StationNode stationNode = stationGraph.get("B");
        assertNotNull(stationNode);
        assertEquals(stationGraph.size(), 5);
    }
}