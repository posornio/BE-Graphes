package org.insa.graphs.algorithm.utils;
import java.time.chrono.HijrahEra;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.assertEquals;

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
import org.junit.Test;


public class DjistraTest extends ShortestPathAlgorithm {
    protected DjistraTest(ShortestPathData data) {
        super(data);
        //TODO Auto-generated constructor stub
    }
  

     ShortestPathData data = getInputData();

    
    private static Graph graph;
    private static Node[] listnode;
    private static Arc a2b, a2c, a2e, b2c, c2d_1, c2d_2, c2d_3, c2a, d2a, d2e, e2d;

    
    

    public static void initAll() throws IOException {}
    @Test
     public void testRandom(){
        int correcte= 0;

        
    

    for (int i=0;i<10;i++){
        Node or = data.getOrigin();
        int id = or.getId();
        float longi = (float) (or.getPoint().getLongitude()+ (int) Math.random() *(300-(-200)));
        float lat =(float) or.getPoint().getLatitude() + (int) Math.random() *(300-(-200));
        Point pt = new Point(longi, lat);
        Node origine =new Node(id, pt);
        data.setOrigin(origine);


        Node dest = data.getOrigin();
        int idD = dest.getId();
        float longiD = (float) (dest.getPoint().getLongitude()+ (int) Math.random() *(300-(-200)));
        float latD =(float) dest.getPoint().getLatitude() + (int) Math.random() *(300-(-200));
        Point ptD = new Point(longiD, latD);
        Node origineD =new Node(idD, ptD);
        data.setOrigin(origineD);
        ShortestPathSolution solBell = new BellmanFordAlgorithm(data).run();
        ShortestPathSolution solDjis = new DijkstraAlgorithm(data).run();
        if (solBell.equals(solDjis)) {
            correcte++; }  
    }
    assertEquals(10, correcte);
}
    



    @Test
    public void testzero(){
        ShortestPathSolution solBell = new BellmanFordAlgorithm(data).run();
        ShortestPathSolution solDjis = new DijkstraAlgorithm(data).run();
    
        if (solBell.getPath().getOrigin().getId() == 0 && solDjis.getPath().getOrigin().getId() == 0 ){
            
            System.out.println("On a pas bougé\n");
            
        }
    }




    @Override
    protected ShortestPathSolution doRun() {
        // TODO Auto-generated method stub
        return null;
    }}