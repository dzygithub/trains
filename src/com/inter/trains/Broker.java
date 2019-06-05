package com.inter.trains;

import com.inter.trains.executor.Executor;
import com.inter.trains.executor.ExecutorFactory;
import com.inter.trains.executor.RouteCounter;
import com.inter.trains.model.GraphModel;

import java.util.ArrayList;
import java.util.List;

public class Broker {

    private List<Executor> executorList;
    private List<String> resultList;
    private GraphModel graphModelInstance;

    public Broker(GraphModel graphModelInstance) {
        this.graphModelInstance = graphModelInstance;
        this.executorList = new ArrayList<Executor>();
        this.resultList = new ArrayList<String>();
    }

    public void receive(List<String> commandList) throws Exception {
        for (String command : commandList) {
            Executor executor = ExecutorFactory.getInstance().createExecutor(command);
            executor.setGraphModel(this.graphModelInstance);
            this.executorList.add(executor);
        }
    }

    public void submit() {
        int i = 1;
        String originalCommand = "";
        for (Executor req : this.executorList) {
            try {
                originalCommand = req.getCommand().getOriginalCommand();
                List<RouteCounter> routeCounterList = (List<RouteCounter>)req.execute();
                String resultStr = this.resultToString(routeCounterList);
                resultStr = String.format("Input #%d Command: %s\nOutput: \n", i, originalCommand) + resultStr;
                this.resultList.add(resultStr);
            } catch (Exception e) {
                this.resultList.add(String.format("Output #%d, Command: %s : %s", i, originalCommand, e.getMessage()));
            }
            i += 1;
        }
    }

    public List<String> getResultList() {
        return this.resultList;
    }

    public String resultToString(List<RouteCounter> routeCounterList){
        String str = "";
        for(RouteCounter routeCounter: routeCounterList){
            int distance = routeCounter.getTotalDistance();
            String nodeStr = String.join("", routeCounter.getStationNodeNameList());
            str += String.format("- route: %s, distance: %d \n", nodeStr, distance);
        }
        return str;
    }
}
