
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author WinSeven
 */
public class Processo extends Thread{
    int id;
    String nome;
    Escalonador esc;
    Semaphore sem;
    int flag;
    int procs;
    RC rc;
    Semaphore sem2;
    Semaphore sem3;
    int times;
    
    public Processo(int id,String nome, Escalonador e, Semaphore s, RC r, Semaphore s2, Semaphore s3){
        this.id = id;
        this.nome = nome;
        this.esc = e;
        sem = s;
        flag = 0;
        procs = 0;
        rc = r;
        sem2 = s2;
        sem3 = s3;
        times=0;
    }
    
    @Override
    public String toString(){        
        return ("ID "+ id + " Nome " +nome);
    }
    
    public void largar(){
        rc.fase3(this);
    }
    
    public void comer() throws InterruptedException{
        sem3.acquire();
        rc.fase2(this);
        //System.out.println("a");
        sem3.release();
    }
    
    public void tentativa() throws InterruptedException{
        //System.out.println("BOOM ROASTED!");
        sem2.acquire();
        if(rc.teste(this)!=0){
            comer();
        }
        sem2.release();
        
    }
    
    public void Pensar(){
        System.out.println("Filosofo "+nome+" esta pensando se a muie ta traindo ele");
    }
    
    @Override
    public void run(){
       
        
        
    }
//    @Override
//    public void run(){
//        int cont = 0;
//        sem.acquireUninterruptibly();
//        while(cont<2){
//            //System.out.println("b");  
//            while(flag==0&&procs<2){
//                //System.out.println("c");
//                if(esc.checa(this)==1){
//                    Pensar();
//                    
////                    try {
////                        tentativa();
////                    } catch (InterruptedException ex) {
////                        Logger.getLogger(Processo.class.getName()).log(Level.SEVERE, null, ex);
////                    }
//                    procs++;
//                    //System.out.println("2");
//                    flag = 1;
//                }
//            }
//            //System.out.println("1");
//            flag=0;
//            try {
//                Thread.sleep(2500);
//            } catch (InterruptedException ex) {
//                Logger.getLogger(Processo.class.getName()).log(Level.SEVERE, null, ex);
//            }
//            sem.release(2);
//            
//
//            cont++;
//        }
//    }
}
