package org.insa.graphs.algorithm;
import org.insa.graphs.model.Point;

import org.insa.graphs.model.Node;

public class LabelStar extends Label {

    public LabelStar(Node sommetCourant) {
        super(sommetCourant);
        //TODO Auto-generated constructor stub
    }
    public double getTotalCost(Node destination){
        double ori = Double.POSITIVE_INFINITY;
        Point ptDest = destination.getPoint();
        double dest = this.getSommet().getPoint().distanceTo(ptDest);

        return ori+dest;
    }
}
