
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
        RC rc = new RC();
        Escalonador e = new Escalonador(sem, rc);
        List<Processo> filosofos = new ArrayList<>();
        filosofos.add(new Processo(1, "Platao", e, sem));
        filosofos.add(new Processo(2, "Aristoteles", e, sem));
        filosofos.add(new Processo(3, "Socrates", e, sem));
        filosofos.add(new Processo(4, "Descartes", e, sem));
        filosofos.add(new Processo(5, "Nietzsche", e, sem));
        e.adiciona(filosofos);
        e.inicia();
        for(Processo p:filosofos){
            p.start();
        }
    }
}
