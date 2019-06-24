
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.Semaphore;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author WinSeven
 */
public class Main {
    public static void main(String[] args) {
        Semaphore sem = new Semaphore(2);
        Semaphore sem2 = new Semaphore(2);
        Semaphore sem3 = new Semaphore(2);
        RC rc = new RC();
        Escalonador e = new Escalonador(sem, rc);
        List<Processo> filosofos = new ArrayList<>();
        filosofos.add(new Processo(1, "Platao", e, sem, rc, sem2,sem3));
        filosofos.add(new Processo(2, "Aristoteles", e, sem, rc, sem2,sem3));
        filosofos.add(new Processo(3, "Socrates", e, sem,rc, sem2,sem3));
        filosofos.add(new Processo(4, "Descartes", e, sem, rc, sem2,sem3));
        filosofos.add(new Processo(5, "Nietzsche", e, sem, rc, sem2,sem3));
        e.adiciona(filosofos);
        
        e.start();
        //filosofos.get(0).start();
        //filosofos.get(2).start();
        //filosofos.get(3).start();
        //filosofos.get(1).start();
        //filosofos.get(4).start();
        //e.inicia();
        /*for(Processo p:filosofos){
            p.start();
        }*/
//        filosofos.get(0).start();
//        filosofos.get(1).start();
    }
}
