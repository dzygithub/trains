package com.inter.trains;

import com.inter.trains.executor.Executor;
import com.inter.trains.executor.ExecutorFactory;
import com.inter.trains.model.GraphModel;

import java.util.ArrayList;
import java.util.List;

public class Broker {

    private List<Executor> executorList;
    private List<Object> resultList;
    private GraphModel graphModelInstance;

    public Broker(GraphModel graphModelInstance) {
        this.graphModelInstance = graphModelInstance;
        this.executorList = new ArrayList<Executor>();
        this.resultList = new ArrayList<Object>();
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
        for (Executor req : this.executorList) {
            try {
                //String originalCommand = req.getCommand().getOriginalCommand();
                Object result = req.execute();
                this.resultList.add(String.format("Output #%d: %s", i, result));
            } catch (Exception e) {
                this.resultList.add(String.format("Output #%d: %s", i, e.getMessage()));
            }
            i += 1;
        }
    }

    public List<Object> getResultList() {
        return this.resultList;
    }


}
