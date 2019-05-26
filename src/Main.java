import com.inter.trains.LoadDataFile;
import com.inter.trains.Broker;
import com.inter.trains.model.GraphModel;

import java.util.List;

public class Main {

    static final String DEFAULT_GRAPH_DATA_PATH = "graph.txt";
    static final String DEFAULT_REQ_DATA_PATH = "command.txt";

    private String[] args;

    public Main(String[] args) {
        this.args = args;
    }

    /**
     * Get the graph data from file, if not pass, use the default
     * 
     * @return
     * @throws Exception
     */
    public List<String> getGraphInitData() throws Exception {
        String graphDataPath = (this.args.length == 0) ? Main.DEFAULT_GRAPH_DATA_PATH : this.args[0];
        return new LoadDataFile(graphDataPath).getDataList();
    }

    /**
     * Get the command data from file, if not pass, use the default
     * 
     * @return
     * @throws Exception
     */
    public List<String> getCommandData() throws Exception {
        String commandDataPath = (this.args.length != 2) ? Main.DEFAULT_REQ_DATA_PATH : this.args[1];
        return new LoadDataFile(commandDataPath).getDataList();
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main(args);

        // load the graph data into memory model instance.
        GraphModel graphModel = new GraphModel();
        graphModel.addRoutes(main.getGraphInitData());

        // parse the commands into broker, then execute
        Broker broker = new Broker(graphModel);
        broker.receive(main.getCommandData());
        broker.submit();

        // output executed result
        for (Object o : broker.getResultList()) {
            System.out.println(o.toString());
        }
    }
}
