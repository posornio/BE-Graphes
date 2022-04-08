package org.insa.graphs.algorithm.shortestpath;

import java.time.chrono.HijrahEra;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.algorithm.Label;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Graph;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;


public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
            
        final ShortestPathData data = getInputData();
        List<Label> labels =AssocierTous(data.getGraph());
        Node origine =data.getOrigin();
        labels.get(origine.getId()).setCost(0.0);
        ShortestPathSolution solution = null;
        // TODO:
        BinaryHeap<Label> remaining_tas= new BinaryHeap<Label>(); 
       
        
        while(!remaining_tas.isEmpty()){
            Label x= remaining_tas.findMin();
            x.setMarque(true);
            for (Arc y:x.getSommet().getSuccessors()){
                //if (y.getDestination().getId())
            }
        }

        return solution;
    }

    public Label AssocierLabel(Node node){
        Label label = new Label(node);
        return label;
        }

    public List<Label> AssocierTous(Graph graph){
        int nb= graph.size();
        List<Label> liste_label= new ArrayList<Label>();
        for (int i=0; i< nb;i++){
            
            liste_label.add(i, AssocierLabel(graph.get(i)));
            

        }
        return liste_label;
        
    }

}
