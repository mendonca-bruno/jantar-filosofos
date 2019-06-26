import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Semaphore;


public class Escalonador extends Thread{
    Queue<Processo> fila1;
    RC rc;
    List<Processo> lista;
    volatile int free;
    
    public Escalonador(Semaphore s, RC r){
        fila1 = new LinkedList<>();
        rc = r;
        lista = new ArrayList<>();
        free= 0;
    }
    
    public synchronized void setFree(int value){this.free=value;}
    public synchronized int getFree(){return this.free;}
    
    public void adiciona(List<Processo> l){
        
        for(Processo proc:l){
            fila1.add(proc);
        }
    }
    public void sincroniza(){ while(free!=2){}free=0;}
    
    public void largaGarfos(Processo p, Processo q){ p.largar();q.largar();} 
    
    public void aloca() throws InterruptedException{System.out.println("Alocando recursos"); Thread.sleep(2000);}
    
    public void addElement(Processo p){ fila1.add(p);}
    
    public int checaFlag(Processo p, Processo q){
        if(p.getFlag()==0 && q.getFlag()==0) return 1;
        else return 0;
    } 
    
    public Processo pegaFirst(){
        Processo aux;
        aux = fila1.element();
        return aux;
    }
    
    public void tira(){ fila1.remove();}    
    
    public void movimenta(){
        Processo aux = fila1.element();
        fila1.remove();
        fila1.add(aux);
    }
    
    public void pensa(){
        Processo aux;
        for(int i=0; i<4; i++){
            aux = pegaFirst();
            if(aux.flag!=1){
                aux.Pensar();
                movimenta();
            }
            else{
               movimenta();
            }
        }
    }
    
    public Processo achaFilosofo(){
        Processo aux = pegaFirst();
        
        if(rc.tentaPegar(aux)!=1){
            while(rc.tentaPegar(aux)!=1){
                movimenta();
                aux = pegaFirst();
            }
        }
        
        rc.aloca(aux);
        if(rc.getAcess()==1) tira();
        return aux;
    }
}
