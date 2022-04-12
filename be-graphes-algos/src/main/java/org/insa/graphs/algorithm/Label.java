package org.insa.graphs.algorithm;

import java.nio.file.attribute.PosixFileAttributeView;
import java.util.List;

import org.insa.graphs.algorithm.Label;
import org.insa.graphs.model.Node;
import org.insa.graphs.model.Arc;



public class Label implements Comparable<Label>{

   private Node sommetCourant;
   private boolean Marque;
   private double cout;
   private Arc pere;

    public Label(Node sommetCourant){
        this.sommetCourant=sommetCourant;
        this.Marque=false;
        this.cout=Double.POSITIVE_INFINITY;
        this.pere=null;
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

    public boolean getMarque(){
        return this.Marque;
    }

    public void setCost(double cost){
        this.cout=cost;
    }
    public void setPere(Arc pere){
        this.pere = pere; 
    }
    public void setMarque(boolean Marque){
        this.Marque = Marque;
    }



    @Override
    public int compareTo(Label other) {
      return  Math.round(Double.compare(getCost(), other.getCost()));

    }
}
