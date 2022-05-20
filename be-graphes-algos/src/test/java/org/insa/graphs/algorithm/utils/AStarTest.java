package org.insa.graphs.algorithm.utils;
import static org.junit.Assert.assertEquals;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import org.insa.graphs.algorithm.ArcInspector;
import org.insa.graphs.algorithm.ArcInspectorFactory;
import org.insa.graphs.algorithm.shortestpath.AStarAlgorithm;
import org.insa.graphs.algorithm.shortestpath.DijkstraAlgorithm;
import org.insa.graphs.algorithm.shortestpath.ShortestPathData;
import org.insa.graphs.algorithm.shortestpath.ShortestPathSolution;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;
import org.insa.graphs.model.Point;
import org.insa.graphs.model.io.BinaryGraphReader;
import org.insa.graphs.model.io.BinaryPathReader;
import org.insa.graphs.model.io.GraphReader;
import org.insa.graphs.model.io.PathReader;
import org.junit.Before;
import org.junit.Test;

public class AStarTest {
        private final String mapName = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/haute-garonne.mapgr";
        private final String pathName = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Paths/path_fr31_insa_bikini_canal.path";
    
           
    
         //ShortestPathData data = "new-zealand";
    
        
        private Graph graph;
        private Path path;
        private Node[] listnode;
        private Arc a2b, a2c, a2e, b2c, c2d_1, c2d_2, c2d_3, c2a, d2a, d2e, e2d;
    
        
    
        //Path longPath = new Path(graph, Arrays.asList(new Arc[] { a2b, b2c, c2d_1, d2e }));
    
        
        @Before
        public void initAll() throws IOException {
            final GraphReader reader = new BinaryGraphReader(new DataInputStream(new BufferedInputStream(new FileInputStream(mapName))));
    
            // TODO: Read the graph.
            graph = reader.read();
    
            final PathReader pathReader = new BinaryPathReader(new DataInputStream(new BufferedInputStream(new FileInputStream(pathName))));
    
            // TODO: Read the path.
            path = pathReader.readPath(graph);
        }
    
        @Test
         public void testRandom() throws IOException{
            int correcte= 0;
    
        for (int i=0;i<2;i++){
            Node or = path.getOrigin();
            int id = or.getId();
            float longi = (float) (or.getPoint().getLongitude()+ (int) Math.random() *(300-(-200)));
            float lat =(float) or.getPoint().getLatitude() + (int) Math.random() *(300-(-200));
            Point pt = new Point(longi, lat);
            Node origine =new Node(id, pt);
            //path.setOrigin(origine);
    
    
            Node dest = path.getOrigin();
            int idD = dest.getId();
            float longiD = (float) (dest.getPoint().getLongitude()+ (int) Math.floor(Math.random() *(300-(-200))));
            float latD =(float) dest.getPoint().getLatitude() +(int) Math.floor( Math.random() *(300-(-200)));
            Point ptD = new Point(longiD, latD);
            Node origineD =new Node(idD, ptD);
            //data.setOrigin(origineD);
            ArcInspector aInspector = ArcInspectorFactory.getAllFilters().get(0);
            ShortestPathData data = new ShortestPathData(graph, origine, origineD, aInspector) ;
            ShortestPathSolution solStar = new AStarAlgorithm(data).run();
            ShortestPathSolution solDjis = new DijkstraAlgorithm(data).run();
            if (solStar.getPath()==solDjis.getPath()) {
                correcte++; }  
        }
        assertEquals(2, correcte);
    }
    
        @Test
        public void testzero() throws IOException{
    
            
            int correcte= 0;
    
            for (int i=0;i<2;i++){
                Node or = path.getOrigin();
                int id = or.getId();
                float longi = (float) (or.getPoint().getLongitude()+ (int) Math.random() *(300-(-200)));
                float lat =(float) or.getPoint().getLatitude() + (int) Math.random() *(300-(-200));
                Point pt = new Point(longi, lat);
                Node origine =new Node(id, pt);
                //path.setOrigin(origine);
        
        
                //data.setOrigin(origineD);
                ArcInspector aInspector = ArcInspectorFactory.getAllFilters().get(0);
                ShortestPathData data = new ShortestPathData(graph, origine, origine, aInspector) ;
                ShortestPathSolution solStar = new AStarAlgorithm(data).run();
                ShortestPathSolution solDjis = new DijkstraAlgorithm(data).run();
                if (solStar.getPath()==solDjis.getPath()) {
                    correcte++; }  
            }
            assertEquals(2, correcte);
        
        }
    
    }
