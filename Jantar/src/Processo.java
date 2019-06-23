
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
    
    public Processo(int id,String nome, Escalonador e, Semaphore s){
        this.id = id;
        this.nome = nome;
        this.esc = e;
        sem = s;
    }
    
    @Override
    public String toString(){        
        return ("ID "+ id + " Nome " +nome);
    }
    
    public void Pensar(){
        System.out.println("Filosofo "+nome+" esta pensando se a muie ta traindo ele");
    }
    
    
    public void run(){
        int cont=0;
        
        try {
            sem.acquire();
            if(esc.checa(this)==1){
                Pensar();
                esc.atualiza();
            }
        } catch (Exception e) {
        }finally{
            sem.release();
        }
    }
}
