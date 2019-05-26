package com.inter.trains.executor;

import com.inter.trains.command.Command;
import com.inter.trains.model.GraphModel;

public interface Executor<V> {

    V execute() throws Exception;

    void setCommand(Command command) throws Exception ;

    Command getCommand() throws Exception;

    void setGraphModel(GraphModel graphModel);
}
