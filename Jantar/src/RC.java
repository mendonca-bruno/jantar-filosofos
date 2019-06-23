
import java.util.ArrayList;
import java.util.List;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author WinSeven
 */
public class RC {
    int garfo[];
    
    public RC(){
        garfo = new int[6];
    }
    
    public void mesa(Processo p) throws InterruptedException{
        if(p.id == 1){
            garfo[1] = 1;
            garfo[2] = 1;
            System.out.println("Filosofo: "+p.nome +" esta comendo ");
            Thread.sleep(5000);
            garfo[1] = 0;
            garfo[2] = 0;
        }else if(p.id == 2){
            garfo[2] = 1;
            garfo[3] = 1;
            System.out.println("Filosofo: "+p.nome +" esta comendo ");
            Thread.sleep(5000);
            garfo[2] = 0;
            garfo[3] = 0;
        }else if(p.id == 3){
            garfo[3] = 1;
            garfo[4] = 1;
            System.out.println("Filosofo: "+p.nome +" esta comendo ");
            Thread.sleep(5000);
            garfo[3] = 0;
            garfo[4] = 0;
        }else if(p.id == 4){
            garfo[4] = 1;
            garfo[5] = 1;
            System.out.println("Filosofo: "+p.nome +" esta comendo ");
            Thread.sleep(5000);
            garfo[4] = 0;
            garfo[5] = 0;
        }else if(p.id == 5){
            garfo[2] = 1;
            garfo[3] = 1;
            System.out.println("Filosofo: "+p.nome +" esta comendo ");
            Thread.sleep(5000);
            garfo[5] = 0;
            garfo[1] = 0;
        }
    }
}
