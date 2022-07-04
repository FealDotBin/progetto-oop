/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop2017.contest2.gruppo18.burger.choice;

import oop2017.contest2.gruppo18.burger.choice.Buffer.BufferServiti;
import oop2017.contest2.gruppo18.burger.choice.Exception.ClienteNonPresenteException;
import java.util.Random;

/**
 *
 * @author gruppo 18
 */
public class Cassiere implements Runnable{
    
    private BufferServiti serviti;
    
    public Cassiere(BufferServiti serviti){
        this.serviti=serviti;
    }
    
    @Override
    public void run(){
        while(true){
            Random random=new Random();
            Integer a[] = new Integer[0]; 
            synchronized(serviti){
                if(serviti.getServiti().isEmpty()){
                    try{
                        serviti.wait();
                    } catch(InterruptedException e){
                    }
                }
            }
            Integer keys[]=serviti.getServiti().keySet().toArray(a); // ritorna un array del tipo passatogli come argomento e contenente le key della Map
            
            int i=random.nextInt(java.lang.reflect.Array.getLength(keys)); // genera un indice casuale che va da 0 alla lunghezza dell'array-1
            try {
                Ordinazione o=serviti.consegnaServiti(keys[i]);
                System.out.println("Ordinazione:\n"+o.toString()+"\nPAGATO!");
                
            } catch (ClienteNonPresenteException ex) {
                System.out.println("Questa ordinazione non è presente nell'elenco delle ordinazioni servite!");
            }
            try {
                Thread.sleep((long)(1000*(5+(random.nextInt(6)))));   //cioè aspetta dai 5 ai 10 secondi
            } catch (InterruptedException ex) {
              }
        }   
    }
}
