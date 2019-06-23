
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
public class Escalonador{
    Queue<Processo> fila1;
    Queue<Processo> fila2;
    Map<Integer,Queue> filaM;
    RC rc;
    int index;
    Processo p;
    
    public Escalonador(Semaphore s, RC r){
        fila1 = new LinkedList<>();
        fila2 = new LinkedList<>();
        filaM = new HashMap<>();
        rc = r;
        index = -1;
        p = null;
    }
    
    public void inicia(){
        filaM.put(0, fila1);
        filaM.put(1, fila2);
        int id = pegaIndex();
        p = (Processo) filaM.get(id).element();
    }
    
    public void adiciona(List<Processo> l){
        for(Processo proc:l){
            if(proc.id%2!=0){
            fila1.add(proc);
            System.out.println("Adicionado a fila 1");
        }
        else{
            fila2.add(proc);
            System.out.println("Adicionado a fila 2");
        }
        }
    }
    
    public int pegaIndex(){
        index = (index+1)%2;
        return index;
    }
    
    public int checa(Processo proc){
        if(p == null){
            //atualiza();
            return 0;
        }
        else if(p.id == proc.id){
            //atualiza();
            return 1;
        }
        else{
            //atualiza();
            return 0;
        }        
    }
    
    public void atualiza(){
        int id = pegaIndex();
        p = (Processo) filaM.get(id).element();
        filaM.get(id).remove();
        filaM.get(id).add(p);
    }
    
  
}
