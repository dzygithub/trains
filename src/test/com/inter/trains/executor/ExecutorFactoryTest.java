package test.com.inter.trains.executor;

import com.inter.trains.executor.Executor;
import com.inter.trains.executor.ExecutorFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class ExecutorFactoryTest {

    @Test
    public void createExecutor() throws Exception {
        String commandStr = "Distance? A-B-C";
        Executor executor = ExecutorFactory.getInstance().createExecutor(commandStr);
        assertEquals(com.inter.trains.executor.DistanceExecutor.class, executor.getClass());
    }

    @Test
    public void createExecutor1() throws Exception {
        try {
            String commandStr = "Distance123? A-B-C";
            Executor executor = ExecutorFactory.getInstance().createExecutor(commandStr);
        } catch (Exception e) {
            assertEquals("command for Distance123 not supported.", e.getMessage());
        }
    }
}