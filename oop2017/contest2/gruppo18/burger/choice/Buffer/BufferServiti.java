/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop2017.contest2.gruppo18.burger.choice.Buffer;

import oop2017.contest2.gruppo18.burger.choice.Exception.ClienteNonPresenteException;
import oop2017.contest2.gruppo18.burger.choice.Ordinazione;
import java.util.*;
import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *Implementa l'interfaccia Serializable e possiede
 * l'attributo “serviti (HashMap&lt;Integer,Ordinazione&gt;)”,
 * che rappresenta il buffer dei clienti serviti,
 * la classe è thread-safe e autobloccante.
 *
 * @author Gruppo 18
 */
public class BufferServiti implements Serializable{
    private HashMap<Integer, Ordinazione> serviti;
/**
 * Crea un nuovo Buffer, questo costruttore viene
 * usato nel file di test quando il file buffer 
 * non esiste e deve essere creato.
 */
    public BufferServiti(){
            serviti = new HashMap();  
    }
/**
 * Restituisce un HashMap dei clienti serviti
 * 
 * @return ritorna serviti
 */
    public synchronized HashMap<Integer, Ordinazione> getServiti() {
        return serviti;
    }
 /**
     * Questo metodo aggiunge una nuova ordinazione 
     * nelle ordinazioni servite
     * 
     * @param ordinazione Contiene la composizione del panino  
     */    
    public synchronized void addServiti(Ordinazione ordinazione){
        serviti.put(ordinazione.getNumUnivoco() ,ordinazione);
        System.out.println("Ordinazione aggiunta al buffer dei serviti dal cuoco!");
        notifyAll();
        try{
            Thread.sleep(10000);//per aspettare 10 secondi prima di preparare il piatto da quando viene aggiunto alle ordinazioni
        } catch(InterruptedException e){
            e.printStackTrace();
        }
    }
/**
  * permette di prelevare un'ordinazione tra quelle
  * dei clienti serviti in base al numero univoco
  * 
  * @param numUnivoco numero che gestisce il cassiere e rappresenta il cliente 
  * @return Restituisce l'ordinazione;
  * @throws ClienteNonPresenteException questa eccezione viene lanciata 
  * nel caso in cui il numero univoco passato come parametro non identifica
  * nessuna ordinazione nel buffer dei clienti serviti
  */    
    public synchronized Ordinazione consegnaServiti(int numUnivoco) throws ClienteNonPresenteException{
        Ordinazione o=serviti.remove(numUnivoco);
        if(o==null)
            throw new ClienteNonPresenteException();
        else{
            notifyAll();
            System.out.println("Ordinazione prelevata dal cassiere!");
            return o;
        }
    }
 /**
   * salva sul file “bufferServiti.txt” le 
   * ordinazioni servite nel buffer
   */
    public synchronized void backupServiti(){
        try{
            wait();
        } catch(InterruptedException ex){
            ex.printStackTrace();
        }
        
        ObjectOutputStream out=null;
        try{
            out = new ObjectOutputStream(new FileOutputStream(new File("bufferServiti.txt")));
            out.writeObject(this);
            System.out.println("BACKUP DEI CLIENTI SERVITI EFFETTUATO!");
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
        Set<Map.Entry<Integer, Ordinazione>> entrySet = serviti.entrySet();
        for(Map.Entry tmp: entrySet){
            s=s+tmp.toString()+"/n";
        }
        return s;
    }  
}
