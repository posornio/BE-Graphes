package org.insa.graphs.algorithm.shortestpath;
import org.insa.graphs.algorithm.Label;
import org.insa.graphs.algorithm.LabelStar;

import org.insa.graphs.model.Node;


public class AStarAlgorithm extends DijkstraAlgorithm {

    public AStarAlgorithm(ShortestPathData data) {
        super(data);
    }
    public Label AssocierLabel(Node node){
        Label label = new LabelStar(node,(ShortestPathData) data);
        return label;
        }
}
