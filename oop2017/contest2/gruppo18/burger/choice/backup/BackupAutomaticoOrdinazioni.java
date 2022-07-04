/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop2017.contest2.gruppo18.burger.choice.backup;

import oop2017.contest2.gruppo18.burger.choice.Buffer.BufferOrdinazioni;

/**
 *
 * @author gruppo 18
 */
public class BackupAutomaticoOrdinazioni implements Runnable{

    private BufferOrdinazioni ordinazioni;
    
    public BackupAutomaticoOrdinazioni(BufferOrdinazioni ordinazioni){
        this.ordinazioni=ordinazioni;
    }
    
    @Override
    public void run() {
        while(true){
            ordinazioni.backupOrdinazioni();      // la funzione invocata provvede a implementare un'attesa passiva fino a quando non cambia lo stato del buffer
        }
    }
    
}
