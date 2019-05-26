package test.com.inter.trains.command;

import com.inter.trains.command.Command;

import static org.junit.Assert.assertEquals;

public class CommandTest {

    Command command;


    @org.junit.Test
    public void parseCommandForNormal() {
        String commandStr1 = "Distance? A-E-D";
        String commandStr2 = "AvailableRoutes? C-E : stops le 3";
        try {
            Command command = new Command(commandStr1);
            assertEquals(3, command.getRoutes().length);
            assertEquals(0, command.getConditionList().size());
            assertEquals("Distance", command.getExecutorName());

            command = new Command(commandStr2);
            assertEquals(2, command.getRoutes().length);
            assertEquals(1, command.getConditionList().size());
            assertEquals(3, command.getConditionList().get(0).getValue());
            assertEquals("stops", command.getConditionList().get(0).getKeyWord().toString());
            assertEquals("le", command.getConditionList().get(0).getOperSymbol().toString());
            assertEquals("AvailableRoutes", command.getExecutorName());

        } catch (Exception e) {
            e.printStackTrace();
            assertEquals(1, 0);
        }
    }

    @org.junit.Test
    public void parseCommandForUnNormal() {
        Command command = null;
        String commandStr1 = "Distance? A-E-D : stop1";
        try {
            command = new Command(commandStr1);
            assertEquals(1, 2);
        } catch (Exception e) {
            assertEquals("condition format is wrong.", e.getMessage());
        }
        commandStr1 = "AvailableRoutes? C-E:";
        try {
            command = new Command(commandStr1);
            assertEquals(1, 1);
        }catch (Exception e) {
            assertEquals(1, 2);
        }
        commandStr1 = "AvailableRoutes? C-E : stops > 3";
        try {
            command = new Command(commandStr1);
            assertEquals(1, 2);
        }catch (Exception e) {
            assertEquals("operSymbol of > is not supported.", e.getMessage());
        }
    }

}