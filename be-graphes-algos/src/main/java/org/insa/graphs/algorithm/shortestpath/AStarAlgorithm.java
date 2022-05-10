package org.insa.graphs.algorithm.shortestpath;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.insa.graphs.algorithm.AbstractSolution.Status;
import org.insa.graphs.algorithm.utils.BinaryHeap;
import org.insa.graphs.algorithm.Label;
import org.insa.graphs.algorithm.LabelStar;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Path;



public class AStarAlgorithm extends DijkstraAlgorithm {

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }
        @Override
    protected ShortestPathSolution doRun() {
            
        final ShortestPathData data = getInputData();
        List<LabelStar> labels =AssocierTous(data.getGraph());
        Node origine =data.getOrigin();
        notifyOriginProcessed(data.getOrigin());

        labels.get(origine.getId()).setCost(0.0);
        ShortestPathSolution solution = null;
        final int nbNodes = data.getGraph().size();

        Arc[] predecessorArcs = new Arc[nbNodes];

        // TODO:
        BinaryHeap<LabelStar> tas= new BinaryHeap<LabelStar>();
        tas.insert(labels.get(origine.getId())); 
        boolean cond= true;
        
        while(!tas.isEmpty() && cond == true){
            LabelStar x= tas.deleteMin();
            x.setMarque(true);
            notifyNodeMarked(x.getSommet());
            for (Arc y:x.getSommet().getSuccessors()){
                LabelStar labelY=labels.get(y.getDestination().getId());
                labelY.setDestination(data.getDestination());
                x.setDestination(data.getDestination());

                if (!labelY.getMarque()){
                    
                    if (x.getTotalCost(x.getDestination())+y.getLength()<labelY.getTotalCost(labelY.getDestination())){
                        if (labelY.getPere()!=null){
                            tas.remove(labelY);
                        }
                        else{
                            notifyNodeReached(y.getDestination());

                        }
                        labelY.setCost(x.getCost()+y.getLength());
                        Arc arc = y;
                        labelY.setPere(y);
                        tas.insert(labelY);
                        predecessorArcs[arc.getDestination().getId()] = arc;
                    }
                    if (labels.get(data.getDestination().getId()).getPere()!=null){
                        notifyDestinationReached(data.getDestination());
                        cond =false;
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
            solution = new ShortestPathSolution(data, Status.OPTIMAL, new Path(data.getGraph(), arcs));
        }
        return solution;
    }
    }
    public List<Label> AssocierTous(Graph graph){
        int nb= graph.size();
        List<Label> liste_label= new ArrayList<Label>();
        for (int i=0; i< nb;i++){
            
            liste_label.add(i, AssocierLabel(graph.get(i)));
            

        }
        //System.out.println("coût djikstra: " + )
        return liste_label;
        
    }