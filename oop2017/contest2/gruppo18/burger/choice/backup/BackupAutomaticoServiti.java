/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package oop2017.contest2.gruppo18.burger.choice.backup;

import oop2017.contest2.gruppo18.burger.choice.Buffer.BufferServiti;

/**
 *
 * @author gruppo 18
 */
public class BackupAutomaticoServiti implements Runnable{

    private BufferServiti serviti;
    
    public BackupAutomaticoServiti(BufferServiti serviti){
        this.serviti=serviti;
    }
    
    @Override
    public void run() {
        while(true){
            serviti.backupServiti();    // la funzione invocata provvede a implementare un'attesa passiva fino a quando non cambia lo stato del buffer
        }
    }
    
}
