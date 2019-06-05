package test.com.inter.trains.executor;

import com.inter.trains.command.Command;
import com.inter.trains.executor.BaseExecutor;
import com.inter.trains.executor.RouteCounter;
import com.inter.trains.executor.ShortestDistanceExecutor;
import com.inter.trains.model.GraphModel;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class ShortestDistanceExecutorTest {

    GraphModel graphModel = null;

    ShortestDistanceExecutor executor = null;

    Command command = null;

    @org.junit.Before
    public void init() {
        executor = new ShortestDistanceExecutor();
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
            executor.setGraphModel(graphModel);
        } catch (Exception e) {
            e.printStackTrace();
            assertEquals(1, 0);
        }
    }

    @Test
    public void execute1() throws Exception {
        command = new Command("ShortestDistance? A-C");
        executor.setCommand(command);
        List<RouteCounter> shortestDistanceRouteList = executor.execute();
        int shortestDistance = (int)shortestDistanceRouteList.get(0).getTotalDistance();
        assertEquals(9, shortestDistance);
    }

    @Test
    public void execute2() throws Exception {
        command = new Command("ShortestDistance? C-C");
        executor.setCommand(command);
        List<RouteCounter> shortestDistanceRouteList = executor.execute();
        int shortestDistance = (int)shortestDistanceRouteList.get(0).getTotalDistance();
        assertEquals(9, shortestDistance);
    }
}