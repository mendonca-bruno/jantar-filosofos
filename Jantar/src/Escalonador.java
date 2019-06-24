import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Escalonador extends Thread{
    Queue<Processo> fila1;
    RC rc;
    List<Processo> lista;
    
    public Escalonador(Semaphore s, RC r){
        fila1 = new LinkedList<>();
        rc = r;
        lista = new ArrayList<>();
    }
    
    public void adiciona(List<Processo> l){
        
        for(Processo proc:l){
            fila1.add(proc);
        }
    }
    
    public Processo pegaFirst(){
        Processo aux;
        aux = fila1.element();
        return aux;
    }
    
    public void tira(){
        fila1.remove();
    }
    
    public void movimenta(){
        Processo aux = fila1.element();
        fila1.remove();
        fila1.add(aux);
    }
    
    public void executa(Processo a, Processo b) throws InterruptedException{
        Thread.sleep(5000);
        if(a.flag==0 && b.flag == 0){
            a.largar();
            b.largar();
        }
        
    }
    
    @Override
    public void run(){       
        
        while(!fila1.isEmpty()&&rc.allClear()==1){
            Processo aux = pegaFirst();
            if(aux.rc.tentaPegar(aux)==1){
                System.out.println("**************");
                tira();
                Processo rest = pegaFirst();
                while(rest.rc.tentaPegar(rest)!=1){
                    movimenta();
                    rest = pegaFirst();
                }                
                aux.flag = 1;
                rest.flag = 1;
                try {
                    aux.comer();
                    rest.comer();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Escalonador.class.getName()).log(Level.SEVERE, null, ex);
                }
                try {
                    executa(aux, rest);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Escalonador.class.getName()).log(Level.SEVERE, null, ex);
                }
                fila1.add(aux);
            }else{
                //do something
            }
        }
        
    }
    
  
}
