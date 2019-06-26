import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        int cont = 0;
        Semaphore sem = new Semaphore(1);
        RC rc = new RC();
        Escalonador e = new Escalonador(sem, rc);
        List<Processo> filosofos = new ArrayList<>();
        Processo p,q;
        
        filosofos.add(new Processo(1, "Platao", e, sem, rc));
        filosofos.add(new Processo(2, "Aristoteles", e, sem, rc));
        filosofos.add(new Processo(3, "Socrates", e, sem,rc));
        filosofos.add(new Processo(4, "Descartes", e, sem, rc));
        filosofos.add(new Processo(5, "Nietzsche", e, sem, rc));
        e.adiciona(filosofos);        
        
        for(Processo i:filosofos){
            i.start();
        }        
        
        while(cont <5&&rc.allClear()==1){
            System.out.println("Alocando recursos . . .");
            System.out.println();
            Thread.sleep(4000);
            System.out.println("************************");
            System.out.println();
            p = e.achaFilosofo();
            q = e.achaFilosofo();
            p.setFlag(1);
            q.setFlag(1);
            e.pensa();
            e.sincroniza();
            if(e.checaFlag(p, q)==1)e.largaGarfos(p, q);
            e.addElement(p);
            cont++;
            System.out.println("************************");
            System.out.println();
            Thread.sleep(5000);
        }
    }
}
