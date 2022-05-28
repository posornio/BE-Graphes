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
import org.insa.graphs.model.Point;
import org.insa.graphs.model.RoadInformation;


public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
            
        final ShortestPathData data = getInputData();
        List<Label> labels =AssocierTous(data.getGraph());
        Node origine =data.getOrigin();
        notifyOriginProcessed(data.getOrigin());

        labels.get(origine.getId()).setCost(0.0);
        ShortestPathSolution solution;
        final int nbNodes = data.getGraph().size();

        Arc[] predecessorArcs = new Arc[nbNodes];

        BinaryHeap<Label> tas= new BinaryHeap<Label>();
        tas.insert(labels.get(origine.getId())); 
        
        while(!tas.isEmpty()){
            Label x= tas.deleteMin();
            x.setMarque(true);
            for (Arc y:x.getSommet().getSuccessors()){
                Label labelY=labels.get(y.getDestination().getId());
                if (!labelY.getMarque()){
                    //double coutAv = labelY.getCost();
                    
                    if (x.getCost()+y.getLength()<labelY.getCost()){
                        if (labelY.getPere()!=null){
                            tas.remove(labelY);
                        }
                        labelY.setCost(x.getCost()+y.getLength());
                        Arc arc = y;
                        labelY.setPere(y);
                        tas.insert(labelY);
                        predecessorArcs[arc.getDestination().getId()] = arc;


                    }
                    
                }
            }
        }

        if (labels.get(data.getDestination().getId()).getPere()==null){
            solution = new ShortestPathSolution(data, Status.INFEASIBLE);

        }
        else{
            notifyDestinationReached(data.getDestination());

            ArrayList<Arc> arcs = new ArrayList<>();
            Arc arc = predecessorArcs[data.getDestination().getId()];
            //Arc arc = labelY.getPere();
            while(arc!=null){
                arcs.add(arc);
                arc = predecessorArcs[arc.getOrigin().getId()];

            }
            Collections.reverse(arcs);
            Path pathSol = new Path(data.getGraph(), arcs);
            solution = new ShortestPathSolution(data, Status.OPTIMAL,pathSol );
        }
        return solution;
    }
    protected ShortestPathSolution doRun2(ShortestPathData data) {
            
        //final ShortestPathData data = getInputData();
        List<Label> labels =AssocierTous(data.getGraph());
        Node origine =data.getOrigin();
        notifyOriginProcessed(data.getOrigin());

        labels.get(origine.getId()).setCost(0.0);
        ShortestPathSolution solution;
        final int nbNodes = data.getGraph().size();
        Path pathTest = new Path(data.getGraph(),data.getOrigin());
        Arc[] predecessorArcs = new Arc[nbNodes];

        BinaryHeap<Label> tas= new BinaryHeap<Label>();
        tas.insert(labels.get(origine.getId())); 
        
        while(!tas.isEmpty()){
            Label x= tas.deleteMin();
            x.setMarque(true);
            for (Arc y:x.getSommet().getSuccessors()){
                Label labelY=labels.get(y.getDestination().getId());
                if (!labelY.getMarque()){
                    
                    if (x.getCost()+y.getLength()<labelY.getCost()){
                        if (labelY.getPere()!=null){
                            tas.remove(labelY);
                        }
                        labelY.setCost(x.getCost()+y.getLength());
                        Arc arc = y;
                        labelY.setPere(y);
                        tas.insert(labelY);
                        predecessorArcs[arc.getDestination().getId()] = arc;


                    }
                    
                }
            }
        }

        if (labels.get(data.getDestination().getId()).getPere()==null){
            solution = new ShortestPathSolution(data, Status.INFEASIBLE);

        }
        else{
            notifyDestinationReached(data.getDestination());

            ArrayList<Arc> arcs = new ArrayList<>();
            Arc arc = predecessorArcs[data.getDestination().getId()];
            while(arc!=null){
                arcs.add(arc);
                arc = predecessorArcs[arc.getOrigin().getId()];

            }
            Collections.reverse(arcs);
            Path pathSol = new Path(data.getGraph(), arcs);
            solution = new ShortestPathSolution(data, Status.OPTIMAL,pathSol );
        }
        return solution;
    }

    public Label AssocierLabel(Node node){
        Label label = new Label(node);
        return label;
        }

    public float getLengthDjis(){
        ShortestPathSolution solution=this.doRun();
        return solution.getPath().getLength();

    }
    public Path getPathDjis(ShortestPathData data){
        ShortestPathSolution solution=this.doRun2(data);
        return solution.getPath();

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
