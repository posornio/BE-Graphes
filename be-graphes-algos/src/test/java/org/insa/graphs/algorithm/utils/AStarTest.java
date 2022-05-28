package org.insa.graphs.algorithm.utils;
import static org.junit.Assert.assertEquals;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.insa.graphs.algorithm.ArcInspector;
import org.insa.graphs.algorithm.ArcInspectorFactory;
import org.insa.graphs.algorithm.shortestpath.AStarAlgorithm;
import org.insa.graphs.algorithm.shortestpath.DijkstraAlgorithm;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;
import org.insa.graphs.algorithm.shortestpath.ShortestPathSolution;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Point;
import org.junit.Test;

public class AStarTest extends DjistraTest {
    public ShortestPathSolution AlgoaComparer(ShortestPathData data){
        return  new AStarAlgorithm(data).run();
    }

    @Override
    public void testRandom() throws IOException, IllegalArgumentException {
        // TODO Auto-generated method stub
        super.testRandom();
    }
    @Test
    public void testPerf(){
        List <Duration> listeBF = new ArrayList<>();
        List <Duration> listeD = new ArrayList<>();
        boolean[] cond ={false,false,false} ;
        for (int i=0;i<3;i++){
            ArcInspector aInspector = ArcInspectorFactory.getAllFilters().get(1);


            Node or = path.getOrigin();
            int id = or.getId();
            float longi = (float) (or.getPoint().getLongitude()+ (int) Math.random() *(10000-(-10000)));
            float lat =(float) or.getPoint().getLatitude() + (int) Math.random() *(10000-(-10000));
            Point pt = new Point(longi, lat);
            Node orMod =new Node(id, pt);    
            Node dest = path.getDestination();
            int idD = dest.getId();
            float longiD = (float) (dest.getPoint().getLongitude()+ (int) Math.floor(Math.random() *(10000-(-10000))));
            float latD =(float) dest.getPoint().getLatitude() +(int) Math.floor( Math.random() *(10000-(-10000)));
            Point ptD = new Point(longiD, latD);
            Node destMod =new Node(idD, ptD);
    
    
            ShortestPathData data = new ShortestPathData(graph, orMod, destMod, aInspector) ;
            ShortestPathSolution solStar = new AStarAlgorithm(data).run();
            ShortestPathSolution solDjis = new DijkstraAlgorithm(data).run();
            listeBF.add(solStar.getSolvingTime());
            listeD.add(solDjis.getSolvingTime());
          
            for (int j=0; j < 3; j++){
                long delta = listeBF.get(j).getNano()- listeD.get(j).getNano();
                System.out.println("\n Delta temps entre Dijkstra et AStar = " + delta);
                if (delta<=0)        cond[j]=true;
                assertEquals(true, cond[j]);

                
            }
        
    
        }
    }

    }
