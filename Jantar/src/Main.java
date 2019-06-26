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
            Thread.sleep(4000); // 4segundos para alocar (2 segundos primeiro filosofo) (2 segundos segundo filosofo)
            System.out.println("************************");
            System.out.println();
            p = e.achaFilosofo(); //pega filosofo que consegue pegar os dois garfos
            q = e.achaFilosofo();
            p.setFlag(1); // muda flag dos processos que conseguiram pegar garfos - assim que a flag for alterada conseguem comer
            q.setFlag(1);
            e.pensa(); //coloca os que nao conseguiram comer para pensar
            e.sincroniza(); //espera a flag do escalonador chegar a 2 que significa que os dois ja comeram
            if(e.checaFlag(p, q)==1)e.largaGarfos(p, q);
            e.addElement(p); //adiciona o primeiro filosofo encontrado ao final da fila
            cont++;
            System.out.println("************************");
            System.out.println();
            Thread.sleep(5000);
        }
    }
}
