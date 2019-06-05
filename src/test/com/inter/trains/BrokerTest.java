package test.com.inter.trains;

import com.inter.trains.Broker;
import com.inter.trains.command.Command;
import com.inter.trains.model.GraphModel;
import org.junit.Test;
import test.com.inter.trains.executor.BaseExecutorImp;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BrokerTest {

    Broker broker;

    @org.junit.Before
    public void init() {
        GraphModel graphModel = null;
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
        broker = new Broker(graphModel);
    }

    public void receive() throws Exception {
        List<String> commandList = new ArrayList<String>();
        commandList.add("Distance? A-B-C");
        commandList.add("Distance? A-E-D");
        this.broker.receive(commandList);
    }

    @Test
    public void submit() throws Exception {
        this.receive();
        this.broker.submit();
        List<String> resultList = this.broker.getResultList();
        assertEquals("Input #1 Command: Distance? A-B-C\n" +
                "Output: \n" +
                "- route: ABC, distance: 9 \n", resultList.get(0).toString());
        assertEquals("Output #2, Command: Distance? A-E-D : NO SUCH ROUTE", resultList.get(1).toString());
    }
}