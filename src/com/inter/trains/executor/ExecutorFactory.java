package com.inter.trains.executor;

import com.inter.trains.command.Command;

import java.lang.reflect.Constructor;

/**
 * factory for create executor according with client command
 */
public class ExecutorFactory {

    static final String PRE_FIX_EXECUTOR_CLASS_NAME = "com.inter.trains.executor";

    private static ExecutorFactory ourInstance = new ExecutorFactory();

    public static ExecutorFactory getInstance() {
        return ourInstance;
    }

    private ExecutorFactory() {
    }

    /**
     * create executor instance leverage reflect mechanism
     *
     * @param commandStr string
     * @return
     * @throws Exception
     */
    public Executor createExecutor(String commandStr) throws Exception {
        Executor executor = null;
        Command command = new Command(commandStr);
        StringBuffer sb = new StringBuffer();
        sb.append(PRE_FIX_EXECUTOR_CLASS_NAME).append(".").append(command.getExecutorName()).append("Executor");
        try {
            Class<Executor> calClass = (Class<Executor>) Class.forName(sb.toString());
            Constructor<Executor> calClassConstructor = calClass.getConstructor();
            executor = calClassConstructor.newInstance();
        } catch (Exception e) {
            throw new Exception(String.format("command for %s not supported.", command.getExecutorName()));
        }
        executor.setCommand(command);
        return executor;
    }
}
