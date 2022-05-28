package org.insa.graphs.algorithm.utils;
import static org.junit.Assert.assertEquals;
import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.insa.graphs.algorithm.ArcInspector;
import org.insa.graphs.algorithm.ArcInspectorFactory;
import org.insa.graphs.algorithm.shortestpath.BellmanFordAlgorithm;
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


public class DjistraTest {

    private final String mapName = "/Users/patrickosornio/Dev/BE-Graphes/haute-garonne.mapgr";
    private final String pathName = "/Users/patrickosornio/Dev/BE-Graphes/path_fr31_insa_bikini_canal.path";

    //private final String mapName = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Maps/haute-garonne.mapgr";
    //private final String pathName = "/mnt/commetud/3eme Annee MIC/Graphes-et-Algorithmes/Paths/path_fr31_insa_bikini_canal.path";

       

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
     public void testRandom() throws IOException, IllegalArgumentException{
        List <ShortestPathSolution> listeBF = new ArrayList<>();
        List <ShortestPathSolution> listeD = new ArrayList<>();
        ArcInspector aInspector = ArcInspectorFactory.getAllFilters().get(1);
        //int correcte=0;
        boolean[] cond ={false,false,false} ;
    for (int i=0;i<2;i++){
        

        Node or = path.getOrigin();
        int id = or.getId();
        float longi = (float) (or.getPoint().getLongitude()+ (int) Math.random() *(10000-(-10000)));
        float lat =(float) or.getPoint().getLatitude() + (int) Math.random() *(10000-(-10000));
        Point pt = new Point(longi, lat);
        Node orMod =new Node(id, pt);
        //path.setOrigin(origine);


        Node dest = path.getDestination();
        int idD = dest.getId();
        float longiD = (float) (dest.getPoint().getLongitude()+ (int) Math.floor(Math.random() *(10000-(-10000))));
        float latD =(float) dest.getPoint().getLatitude() +(int) Math.floor( Math.random() *(10000-(-10000)));
        Point ptD = new Point(longiD, latD);
        Node destMod =new Node(idD, ptD);
        //data.setOrigin(origineD);
        ShortestPathData data = new ShortestPathData(graph, orMod, destMod, aInspector) ;
        ShortestPathSolution solBell = new BellmanFordAlgorithm(data).run();
        ShortestPathSolution solDjis = new DijkstraAlgorithm(data).run();
        listeBF.add(solBell);
        listeD.add(solDjis);
    }


    ShortestPathData data = new ShortestPathData(graph, path.getOrigin(), path.getOrigin(), aInspector) ;
    ShortestPathSolution solBell = new BellmanFordAlgorithm(data).run();
    ShortestPathSolution solDjis = new DijkstraAlgorithm(data).run();
    listeBF.add(solBell);
    listeD.add(solDjis);

    for (int j=0; j < 3; j++){
        //float belLenght = solBell.getPath().getLength();
        //float dijLen = new DijkstraAlgorithm(data).getPathDjis(data).getLength();
        if (listeBF.get(j).getPath()==null && listeD.get(j).getPath()==null){
            cond[j]=true;
            //Les deux paths sont invalides ie dest=origine
            //throw new IllegalArgumentException("Path is not Valid!!") ;
        } 
        else if ((listeBF.get(j).getPath().getLength()>=listeD.get(j).getPath().getLength())) {
            cond[j]=true;
    }
    assertEquals(true, cond[j]);
}



}}