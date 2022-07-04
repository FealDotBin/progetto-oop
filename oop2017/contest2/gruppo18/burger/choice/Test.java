/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop2017.contest2.gruppo18.burger.choice;

import oop2017.contest2.gruppo18.burger.choice.Interface.UI;
import oop2017.contest2.gruppo18.burger.choice.Buffer.BufferOrdinazioni;
import oop2017.contest2.gruppo18.burger.choice.Buffer.BufferServiti;
import oop2017.contest2.gruppo18.burger.choice.backup.BackupAutomaticoServiti;
import oop2017.contest2.gruppo18.burger.choice.backup.BackupAutomaticoOrdinazioni;
import java.io.*;
import java.util.*;

/**
 *
 * @author gruppo 18
 */
public class Test {
    
    public static void main(String[] args) {
        
        System.out.println("BENVENUTO NELLA SEZIONE AMMINISTRATORE DI BURGER CHOSE");
        Scanner s = new Scanner(System.in);
        String risposta;
        BufferOrdinazioni buffOrdinazioni=null;
        BufferServiti buffServiti=null;
        int n=0; //numero cuochi preso in input
        do {
            System.out.println("Vuoi caricare i buffer da file? (S/N): ");
            risposta = s.next().toLowerCase();
        } while ((!risposta.equals("s")) && (!risposta.equals("n")));

        if (risposta.equals("s")) {
            ObjectInputStream in1 = null;
            ObjectInputStream in2 =null;
            try{
                in1 = new ObjectInputStream(new BufferedInputStream(new FileInputStream("bufferOrdinazioni.txt")));
                in2 = new ObjectInputStream(new BufferedInputStream(new FileInputStream("bufferServiti.txt")));
                buffOrdinazioni = (BufferOrdinazioni) in1.readObject();
                buffServiti = (BufferServiti) in2.readObject();
                System.out.println("Buffer caricati da file correttamente!");
            } catch(FileNotFoundException e){
                System.out.println("File di backup non esistenti!\nVerranno creati dei nuovi buffer!");
                buffOrdinazioni = new BufferOrdinazioni();
                buffServiti = new BufferServiti();
                
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } finally{
               if(in1!=null )
               try {
                   in1.close();
               } catch (IOException ex) {
                   ex.printStackTrace();
               }
               if(in2!=null )
               try {
                   in2.close();
               } catch (IOException ex) {
                   ex.printStackTrace();
               }
            }  
        } else {
            buffOrdinazioni = new BufferOrdinazioni();
            buffServiti = new BufferServiti();
        }
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        do{
            System.out.println("Inserire il numero di cuochi: ");
            String line = null;
            try {
                line = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try{
                n = Integer.parseInt(line);
                if(n<1)
                    System.out.println("Devi inserire un numero positivo!");
            } catch(NumberFormatException e){
                System.out.println("Devi inserire un numero!");
            }
        } while (n<1);   //così chiede sempre il numero dei cuochi sia se inseriamo un numero minore di 1 che una stringa
                
        LinkedList<Cuoco> listaCuochi = new LinkedList<Cuoco>();
        for (int i = 0; i < n; i++) {
        System.out.println("Inserire il nome del cuoco "+(i+1)+": ");
        String cognome = null;
        try {
        cognome = br.readLine();
        } catch (IOException e) {
        e.printStackTrace();
        }
			
        listaCuochi.add(new Cuoco(cognome, buffOrdinazioni, buffServiti));
        }
		
        System.out.println("\nAVVIO RISTORANTE\n");
        
        //inizializzazione interfaccia utente
       
        UI ui=new UI(buffOrdinazioni);
        Thread tui= new Thread(ui);
        tui.start();
		
        // Inizializzazione cuochi
        Iterator<Cuoco> it = listaCuochi.iterator();
        while (it.hasNext()) {
        Thread tcuoco = new Thread(it.next());
        tcuoco.start();
        }
		
        // Inizializzazione cassiere
        Cassiere cassiere = new Cassiere(buffServiti);
        Thread tcassiere = new Thread(cassiere);
        tcassiere.start();
		
        // Inizializzazione dei backup
        BackupAutomaticoOrdinazioni backupOrdinazioni = new BackupAutomaticoOrdinazioni(buffOrdinazioni);
        Thread tbackupOrdinazioni = new Thread(backupOrdinazioni);
        tbackupOrdinazioni.start();
        BackupAutomaticoServiti backupServiti = new BackupAutomaticoServiti(buffServiti);
        Thread tbackupServiti = new Thread(backupServiti);
        tbackupServiti.start();
	}
}
