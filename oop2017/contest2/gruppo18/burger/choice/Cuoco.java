/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop2017.contest2.gruppo18.burger.choice;

import oop2017.contest2.gruppo18.burger.choice.Buffer.BufferOrdinazioni;
import oop2017.contest2.gruppo18.burger.choice.Buffer.BufferServiti;
import java.util.*;

/**
 *
 * @author gruppo 18
 */
public class Cuoco implements Runnable{

    private String cognome;
    private BufferOrdinazioni ordinazioni;
    private BufferServiti serviti;
    
    public Cuoco(String cognome, BufferOrdinazioni ordinazioni, BufferServiti serviti){
        this.cognome=cognome;
        this.ordinazioni=ordinazioni;
        this.serviti=serviti;
    }
    
   /**
    * Il metodo run() è implementato in modo tale
    * che il cuoco si preoccupa di prelevare un'ordinazione
    * dal buffer delle ordinazioni, preparare i rispettivi panini
    * (qui il cuoco aspetta dai 5 ai 10 secondi, per semplicità di funzionamento),
    * e infine aggiungere l'ordinazione completata nel buffer dei clienti serviti.
    */
    @Override
    public void run() {
        
        while(true){
            
            Ordinazione ordinazione=ordinazioni.consegnaOrdinazione();
            System.out.println("Il cuoco "+this.cognome + " sta preparando l'ordinazione numero " + ordinazione.getNumUnivoco() );
            Random random=new Random();
            try {
                Thread.sleep((long)(1000*(5+(random.nextInt(6)))));   //cioè aspetta dai 5 ai 10 secondi
            } catch (InterruptedException ex) {
              }
            System.out.println("Piatto pronto: "+ordinazione.toString());
            serviti.addServiti(ordinazione);
            try {
                Thread.sleep((long)(1000*(5+(random.nextInt(6)))));   //cioè aspetta dai 5 ai 10 secondi
            } catch (InterruptedException ex) {
              }
        }  
    }
}
