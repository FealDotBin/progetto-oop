/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop2017.contest2.gruppo18.burger.choice.Buffer;

import oop2017.contest2.gruppo18.burger.choice.Exception.OrdinazioneNonPresenteException;
import oop2017.contest2.gruppo18.burger.choice.Ordinazione;
import java.util.*;
import java.io.*;
import java.util.logging.*;

/**
 *
 * @author gruppo 18
 */
public class BufferOrdinazioni implements Serializable{
    private LinkedList<Ordinazione> ordinazioni;
    /**
     * Il costruttore viene usato solo se si vuole creare
     * un buffer nuovo (vuoto), e non se si vuole caricare
     * la lista delle ordinazioni dal file di backup.
     */
    public BufferOrdinazioni(){
            ordinazioni = new LinkedList();
    }

    public synchronized LinkedList<Ordinazione> getOrdinazioni() {
        return ordinazioni;
    }
  
    public synchronized void addOrdinazione(Ordinazione ordinazione){
        ordinazioni.add(ordinazione);
        System.out.println("Ordinazione aggiunta al buffer delle ordinazioni!");
        notifyAll();
    }
    /**
     * 
     * @param numUnivoco Identifica il Cliente
     * @throws OrdinazioneNonPresenteException  Viene lanciata se il cuoco non trova l'ordinazione
     */
    public synchronized void removeOrdinazione(int numUnivoco) throws OrdinazioneNonPresenteException{
        Ordinazione o = new Ordinazione(numUnivoco);
        if(!ordinazioni.remove(o)) 
            throw new OrdinazioneNonPresenteException();
        notifyAll();
    }
    
    public synchronized Ordinazione consegnaOrdinazione(){
        while(ordinazioni.isEmpty()){
            try{
                wait();
            } catch(InterruptedException ex){
                ex.printStackTrace();
            }
        }
        System.out.println("Ordinazione tolta dal buffer delle ordinazioni!");
        notifyAll();
        return ordinazioni.remove();
    }
    /**
     * Il file dal quale caricare ha sempre lo stesso nome,
     * ossia “BufferOrdinazioni.txt” che è il file di backup appunto.
     */
    public synchronized void backupOrdinazioni(){
        
        
        try{
            wait();
        } catch (InterruptedException ex){   
            ex.printStackTrace();
        }
        
        ObjectOutputStream out=null;
        try{
            out = new ObjectOutputStream(new FileOutputStream(new File("bufferOrdinazioni.txt")));
            out.writeObject(this);
            System.out.println("backup delle ordinazioni effettuato!");
        } catch (IOException ex) {
            Logger.getLogger(BufferOrdinazioni.class.getName()).log(Level.SEVERE, null, ex);
        } finally{
            try {
                out.close();
            } catch (IOException ex) {
                Logger.getLogger(BufferOrdinazioni.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
    }

    @Override
    public synchronized String toString() {
        String s="";
        for(Ordinazione tmp: ordinazioni){
            s=s+tmp.toString()+"\n";
        }
        return s;
    }  
}
