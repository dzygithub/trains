package test.com.inter.trains.executor;

import com.inter.trains.command.Command;
import com.inter.trains.command.Condition;
import com.inter.trains.command.KeyWord;
import com.inter.trains.command.RelationSymbol;
import com.inter.trains.executor.BaseExecutor;
import com.inter.trains.executor.RouteCounter;
import com.inter.trains.model.GraphModel;
import com.inter.trains.model.StationNode;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class BaseExecutorTest {

    GraphModel graphModel = null;

    BaseExecutor baseExecutor = null;

    Command command = null;

    @org.junit.Before
    public void init() {
        baseExecutor = new BaseExecutorImp();
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
            baseExecutor.setGraphModel(graphModel);

            command = new Command("Distance? A-B-C");
            baseExecutor.setCommand(command);

        } catch (Exception e) {
            e.printStackTrace();
            assertEquals(1, 0);
        }
    }

    @Test
    public void getDistance() throws Exception {
        HashMap<String, StationNode> map = this.baseExecutor.getStationGraphMap();
        try {
            StationNode startStationNode = map.get("A");
            StationNode endStationNode = map.get("F");
            int distance = this.baseExecutor.getDistance(startStationNode, endStationNode);
        } catch (Exception e) {
            assertEquals("NO SUCH ROUTE", e.getMessage());
        }
        try {
            StationNode startStationNode = map.get("A");
            StationNode endStationNode = map.get("E");
            int distance = this.baseExecutor.getDistance(startStationNode, endStationNode);
            assertEquals(7, distance);
        } catch (Exception e) {
            assertEquals(1, 2);
        }
    }

    @Test
    public void extendRouteCounter() {
        RouteCounter counterT = new RouteCounter();
        String stationNodeName = "D";
        Integer distance = 10;
        counterT.setStationNodeNameList(new ArrayList(Arrays.asList(new String[]{"A", "B", "C"})));
        counterT.setTotalStops(3);
        counterT.setTotalDistance(10);
        RouteCounter rc = this.baseExecutor.extendRouteCounter(counterT, stationNodeName, distance);
        assertEquals(20, (int) rc.getTotalDistance());
        assertEquals(4, (int) rc.getTotalStops());
        assertEquals(10, (int) counterT.getTotalDistance());
        assertEquals(3, (int) counterT.getTotalStops());
        assertEquals(3, counterT.getStationNodeNameList().size());
        assertEquals(4, rc.getStationNodeNameList().size());
    }

    @Test
    public void getCommandRoutes() throws Exception {
        this.baseExecutor.getCommandRoutes();
        try {
            Command tmpCommand = new Command("Distance? A-XF");
            baseExecutor.setCommand(command);
        } catch (Exception e) {
            assertEquals("NO SUCH ROUTE", e.getMessage());
        }
    }
}