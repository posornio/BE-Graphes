package org.insa.graphs.algorithm;

import java.nio.file.attribute.PosixFileAttributeView;
import java.util.List;

import org.insa.graphs.algorithm.Label;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Arc;
import org.insa.graphs.model.Point;




public class Label implements Comparable<Label>{

   private Node sommetCourant;
   private Node destination;
   private boolean Marque;
   private double cout;
   private Arc pere;

    public Label(Node sommetCourant){
        this.sommetCourant=sommetCourant;
        this.Marque=false;
        this.cout=Double.POSITIVE_INFINITY;
        this.pere=null;
        this.destination=null;
    }
    
    public Node getSommet(){
        return this.sommetCourant;
    }

    public double getCost(){
        return this.cout;
    }
    public Arc getPere(){
        return this.pere;
    }
    public Node getDestination(){
        return this.destination;
    }

    public boolean getMarque(){
        return this.Marque;
    }

    public void setCost(double cost){
        this.cout=cost;
    }
    public void setPere(Arc pere){
        this.pere = pere; 
    }
    public void setPere(Node destination){
        this.destination = destination; 
    }
    public void setMarque(boolean Marque){
        this.Marque = Marque;
    }
    public double getTotalCost(Node destination)
    {   Label labDest = new Label(destination);
        Arc arcret=null;
        for (Arc i:this.getSommet().getSuccessors()){
            if (i.getOrigin().equals(this.sommetCourant) && i.getDestination().equals(destination)) {
                arcret=i;
            } 
        }
        if (arcret==null)
            return -1;
        return this.getCost()+(labDest.getCost()-arcret.getLength());
    } 


    
    
    public int compareTo(Label other) {
        
        return  Math.round(Double.compare(this.getTotalCost(this.getDestination()), other.getTotalCost(other.getDestination())));

    }

 
}
