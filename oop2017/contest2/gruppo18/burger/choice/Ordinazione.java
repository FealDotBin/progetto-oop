/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop2017.contest2.gruppo18.burger.choice;

/**
 *
 * @author gruppo 18
 */

import java.io.*;
import java.util.*;

public class Ordinazione implements Serializable{
    private static int numIncrementale=1;
    private final int numUnivoco;
    private double conto;
    private List<Panino> panini;
    private String cognome;
    
    public Ordinazione(String cognome){
        panini = new LinkedList();
        conto=0.0;
        numUnivoco=numIncrementale;
        numIncrementale++;
        this.cognome=cognome;
    }
    
    public Ordinazione(int numUnivoco){
        this.numUnivoco=numUnivoco;
        panini = null;
        conto=0.0;
        this.cognome=null;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public void setConto(double conto) {
        this.conto = conto;
    }

    public String getCognome() {
        return cognome;
    }

    public int getNumUnivoco() {
        return numUnivoco;
    }

    public double getConto() {
        return conto;
    }

    public List<Panino> getPanini() {
        return panini;
    }

    public void aggiungiPanino(Panino panino){
       panini.add(panino);
    }
    
    public void calcolaConto(){
     
       for (Panino x : panini){
          this.conto = this.conto + x.getPrezzo();
       }
       if (panini.size() > 9){
           this.conto = this.conto * 0.80;
       } else{
           if(panini.size() > 3)
               this.conto = this.conto * 0.85;
       }
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + this.numUnivoco;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ordinazione other = (Ordinazione) obj;
        if (this.numUnivoco != other.numUnivoco) {
            return false;
        }
        return true;
    }
    
    @Override 
    public String toString(){
        String s = "";
        for(Panino x : panini)
            s += x.toString() + "\n";
        
        return "Cognome: " + cognome + ", Numero Univoco: " + numUnivoco + "\n Ordinazione: \n"
                + s + "\nPrezzo finale: €" + conto;
    }
    
}

