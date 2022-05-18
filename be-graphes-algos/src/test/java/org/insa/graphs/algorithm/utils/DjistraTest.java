package org.insa.graphs.algorithm.utils;
import java.time.chrono.HijrahEra;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.io.IOException;
import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.algorithm.ArcInspector;
import org.insa.graphs.algorithm.Label;
import org.insa.graphs.algorithm.shortestpath.BellmanFordAlgorithm;
import org.insa.graphs.algorithm.shortestpath.DijkstraAlgorithm;
import org.insa.graphs.algorithm.shortestpath.ShortestPathAlgorithm;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;
import org.insa.graphs.algorithm.shortestpath.ShortestPathSolution;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;
import org.insa.graphs.model.Point;
import org.insa.graphs.model.RoadInformation;


public class DjistraTest extends ShortestPathAlgorithm {
    protected DjistraTest(ShortestPathData data) {
        super(data);
        //TODO Auto-generated constructor stub
    }
  

    final static ShortestPathData data = getInputData();

    
    private static Graph graph;
    private static Node[] listnode;
    private static Arc a2b, a2c, a2e, b2c, c2d_1, c2d_2, c2d_3, c2a, d2a, d2e, e2d;

    
    

    public static void initAll() throws IOException {
        
    

    for (int i=0;i<10;i++){
        data.


    }

    ShortestPathSolution solBell = new BellmanFordAlgorithm(data).run();
    ShortestPathSolution solDjis = new DijkstraAlgorithm(data).run();
    boolean correcte= false;
    if (solBell.equals(solDjis)) {
        correcte=true;
    }


}




    @Override
    protected ShortestPathSolution doRun() {
        // TODO Auto-generated method stub
        return null;
    }}