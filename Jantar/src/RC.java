
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
    int acess;
    
    public RC(){
        garfo = new int[6];
        acess = 0;
    }
    
    public int teste(Processo p){
        
        if(p.id!=5){
            //System.out.println("an");
            if(garfo[p.id]!=1 && garfo[(p.id+1)]!=1){
                garfo[p.id]=1;
                garfo[(p.id+1)]=1;
                return 1;
            }else return 0;
        }else{
            //System.out.println("ab");
            if(garfo[p.id]!=1 && garfo[1]!=1){
                garfo[p.id]=1;
                garfo[1]=1;
                return 1;
            }else return 0;
        }
    }
    
    public void fase2(Processo p) throws InterruptedException{
        if(p.id!=5){
            System.out.println("Filosofo "+p.nome+" pegou o garfo "+ p.id+" e "+(p.id+1));
            System.out.println("Filosofo " +p.nome+" esta comendo");
            //Thread.sleep(5000);
            //garfo[p.id] = 0;
            //garfo[(p.id+1)] = 0;
            //p.Pensar();
        }else{
            System.out.println("Filosofo "+p.nome+" pegou o garfo "+ p.id+" e "+1);
            System.out.println("Filoso " +p.nome+" esta comendo");
            //Thread.sleep(5000);
        }
    }
    
    public void fase3(Processo p){
        if(p.id!=5){
            garfo[p.id] = 0;
            garfo[(p.id+1)] = 0;
        }else{
            garfo[p.id] = 0;
            garfo[1] = 0;
        }
    }
    
    public int allClear(){
        int aux = 0;
        for(int i=0; i<6; i++){
            if(garfo[i]==0) aux++;
        }
        if (aux==6) return 1;
        else return 0;
    }
    
//    public synchronized int tentaPegar(Processo p) throws InterruptedException{
//        while(acess!=0){
//            //System.out.println("pinto");
//            wait();
//        }
//        acess = 1;
//        if(p.id!=5){
//            System.out.println("Filoso "+p.nome+" tentou pegar o garfo "+p.id+" e o garfo "+(p.id+1));
//            if(garfo[p.id]!=1 && garfo[(p.id+1)]!=1){
//                pegaGarfo(p);
//                p.Pensar();
//                acess = 0;
//                notifyAll();
//                return 1;
//            }else{
//                p.Pensar();
//                acess = 0;
//                notifyAll();
//                return 0;
//            }
//            
//        }else{
//            System.out.println("Filoso "+p.nome+" tentou pegar o garfo "+p.id+" e o garfo "+1);
//            if(garfo[p.id]!=1 && garfo[1]!=1){
//                pegaGarfo(p);
//                p.Pensar();
//                acess = 0;
//                notifyAll();
//                return 1;
//            }
//            p.Pensar();
//            acess = 0;
//            notifyAll();
//            return 0;
//        }
//    }
    
    public void pegaGarfo (Processo p) throws InterruptedException{
        if(p.id == 1){
            garfo[1] = 1;
            garfo[2] = 1;
            System.out.println("Filosofo: "+p.nome +" esta comendo ");
            //Thread.sleep(5000);
            garfo[1] = 0;
            garfo[2] = 0;
        }else if(p.id == 2){
            garfo[2] = 1;
            garfo[3] = 1;
            System.out.println("Filosofo: "+p.nome +" esta comendo ");
            //Thread.sleep(5000);
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
            //Thread.sleep(5000);
            garfo[4] = 0;
            garfo[5] = 0;
        }else if(p.id == 5){
            garfo[5] = 1;
            garfo[1] = 1;
            System.out.println("Filosofo: "+p.nome +" esta comendo ");
            //Thread.sleep(5000);
            garfo[5] = 0;
            garfo[1] = 0;
        }
    }
}
