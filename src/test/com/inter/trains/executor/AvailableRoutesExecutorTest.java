package test.com.inter.trains.executor;

import com.inter.trains.command.Command;
import com.inter.trains.executor.AvailableRoutesExecutor;
import com.inter.trains.executor.RouteCounter;
import com.inter.trains.executor.ShortestDistanceExecutor;
import com.inter.trains.model.GraphModel;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.*;

public class AvailableRoutesExecutorTest {


    GraphModel graphModel = null;

    AvailableRoutesExecutor executor = null;

    Command command = null;

    @org.junit.Before
    public void init() {
        executor = new AvailableRoutesExecutor();
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
        command = new Command("AvailableRoutes? C-C : stops le 3");
        executor.setCommand(command);
        List<RouteCounter> routeList = executor.execute();
        int availableRoutesCount = (int)routeList.size();
        assertEquals(2, availableRoutesCount);
    }

    @Test
    public void execute2() throws Exception {
        command = new Command("AvailableRoutes? A-C : stops eq 4");
        executor.setCommand(command);
        List<RouteCounter> routeList = executor.execute();
        int availableRoutesCount = (int)routeList.size();
        assertEquals(3, availableRoutesCount);
    }


    @Test
    public void execute3() throws Exception {
        command = new Command("AvailableRoutes? C-C : distance lt 30");
        executor.setCommand(command);
        List<RouteCounter> routeList = executor.execute();
        int availableRoutesCount = (int)routeList.size();
        assertEquals(7, availableRoutesCount);
    }


}