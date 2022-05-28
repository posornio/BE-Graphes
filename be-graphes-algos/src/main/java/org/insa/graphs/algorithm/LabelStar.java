package org.insa.graphs.algorithm;

import javax.xml.crypto.Data;

import org.insa.graphs.algorithm.shortestpath.ShortestPathData;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Point;

public class LabelStar extends Label {
    Node destination;
    public LabelStar(Node sommetCourant,ShortestPathData data) {
        super(sommetCourant);
        this.destination = data.getDestination();
    }
    
    public Node getDestination(){
        return this.destination;
    }
public double getTotalCost() {
    return  this.getCost()+ Point.distance(this.getSommet().getPoint(), destination.getPoint());

  }}