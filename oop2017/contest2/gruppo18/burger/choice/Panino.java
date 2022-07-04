/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop2017.contest2.gruppo18.burger.choice;

import java.io.Serializable;

/**
 *
 * @author gruppo 18
 */
public class Panino implements Serializable {
    private int extra;
    private double prezzo;
    private String descrizione;
    
    public Panino(int extra, String descrizione, double prezzoStandard){
        this.extra=extra;
        this.descrizione=descrizione;
        if(extra < 1)
            this.prezzo=prezzoStandard;
        else
            this.prezzo= prezzoStandard + 0.50 * extra;
    }

    public int getExtra() {
        return extra;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
    
    public void setPrezzo(double prezzo){
        this.prezzo=prezzo;
    }
    
    public void setExtra(int extra){
        this.extra=extra;
    }
    
    @Override 
    public String toString(){
       return descrizione + " Prezzo panino: €" + prezzo; 
    }
}
