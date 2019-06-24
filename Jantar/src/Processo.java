import java.util.concurrent.Semaphore;

public class Processo extends Thread{
    int id;
    String nome;
    Escalonador esc;
    Semaphore sem;
    int flag;
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
    
    @Override
    public String toString(){        
        return ("ID "+ id + " Nome " +nome);
    }
    
    public void largar(){
        rc.liberarGarfos(this);
    }
    
    public void comer() throws InterruptedException{
        if(flag==1&&times<2){
            sem.acquire();
            rc.pegaGarfo(this);
            flag = 0;
            times++;
            sem.release();
        }
        
    } 
    
    public void Pensar(){
        System.out.println("Filosofo "+nome+" esta pensando se a muie ta traindo ele");
    }
    
}
