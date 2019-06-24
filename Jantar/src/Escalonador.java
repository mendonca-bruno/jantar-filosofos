
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;
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
public class Escalonador extends Thread{
    Queue<Processo> fila1;
    Queue<Processo> fila2;
    Map<Integer,Queue> filaM;
    RC rc;
    int index;
    Processo p;
    List<Processo> lista;
    int cont;
    
    public Escalonador(Semaphore s, RC r){
        fila1 = new LinkedList<>();
        fila2 = new LinkedList<>();
        filaM = new HashMap<>();
        rc = r;
        index = -1;
        p = null;
        lista = new ArrayList<>();
        cont = 0;
    }
    
    /*public void inicia(){
        filaM.put(0, fila1);
        filaM.put(1, fila2);
        int id = pegaIndex();
        p = (Processo) filaM.get(id).element();
        lista.add(p);
        if(rc.allClear()==1){
            System.out.println("done");
            p.rc.teste(p);
        }
        //p.perm = 1;
        
    }*/
    
    public void adiciona(List<Processo> l){
        
        for(Processo proc:l){
            fila1.add(proc);
        }
    }
    
    public int pegaIndex(){
        index = (index+1)%2;
        return index;
    }
    
    public int checa(Processo proc){
        if(p == null)return 0;
        
        else if(p.id == proc.id){
            atualiza();
            return 1;
        }
        else if((p.id!=proc.id)&&(p.procs==2)){
            //System.out.println("aqui");
            atualiza();
            return 1;
        }
        else return 0;       
    }
    
    public Processo atualiza(){
        Processo aux;
        aux = (Processo) filaM.get(index).element();
        filaM.get(index).remove();
        filaM.get(index).add(aux);
        index = pegaIndex();
        aux = (Processo) filaM.get(index).element();
        return aux;
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
    
    public void executa() throws InterruptedException{

        for(Processo p:lista){
            p.flag = 1;
        }

        Thread.sleep(5000);
        
        for(Processo p:lista){
            if(p.flag == 0){

                p.largar();
            }
        }
        lista.clear();
        
    }
    
    @Override
    public void run(){
        
        
        while(!fila1.isEmpty()&&rc.allClear()==1){
            Processo aux = pegaFirst();
            if(aux.rc.teste(aux)==1){
                System.out.println("here1");
                lista.add(aux);
                tira();
                //System.out.println(pegaFirst().nome);
                Processo rest = pegaFirst();
                while(rest.rc.teste(rest)!=1){
                    movimenta();
                    rest = pegaFirst();
                }
                lista.add(rest);
                try {
                    System.out.println("here");
                    executa();
                    fila1.add(aux);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Escalonador.class.getName()).log(Level.SEVERE, null, ex);
                }
            }else{
                //System.out.println("here2");
            }
        }
        
    }
    
  
}
