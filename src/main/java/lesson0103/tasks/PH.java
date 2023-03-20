package lesson0103.tasks;

import java.util.concurrent.Phaser;

public class PH {
    public static void main(String[] args) {
        Phaser phaser=new Phaser(2);
       // new Washer()
    }

static class Washer extends Thread{
        Phaser phaser;
       public Washer (Phaser phaser){
           this.phaser=phaser;
       } 
}
    
}
