import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Processo extends Thread{
    int id;
    String nome;
    Escalonador esc;
    Semaphore sem;
    volatile int flag;
    RC rc;
    int times;
    
    public Processo(int id,String nome, Escalonador e, Semaphore s, RC r){
        this.id = id;
        this.nome = nome;
        this.esc = e;
        sem = s;
        flag = 0;
        rc = r;
        times=0;
    }
    
    public synchronized void setFlag(int value){this.flag=value;}
    public synchronized int getFlag(){return this.flag;}
    
    @Override
    public String toString(){        
        return ("ID "+ id + " Nome " +nome);
    }
    
    public void largar(){
        rc.liberarGarfos(this);
    }
    
    public void comer() throws InterruptedException{
        if(flag==1){
            sem.acquire();
            rc.pegaGarfo(this);
            flag = 0;
            times++;
            sem.release();
        }
        
    } 
    
    public void Pensar(){
        System.out.println("Filosofo "+nome+" esta pensando...");
    }
    
    
    @Override
    public void run(){
        boolean roda = true;
        while(roda){
            if(flag==1){
                try {
                    comer();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Processo.class.getName()).log(Level.SEVERE, null, ex);
                }
                esc.setFree((esc.getFree()+1));
                if (times == 2) roda = false;
            }
            
        }
        
    }
    
}
