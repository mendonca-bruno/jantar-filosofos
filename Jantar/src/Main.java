import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) {
        Semaphore sem = new Semaphore(1);
        RC rc = new RC();
        Escalonador e = new Escalonador(sem, rc);
        List<Processo> filosofos = new ArrayList<>();
        filosofos.add(new Processo(1, "Platao", e, sem, rc));
        filosofos.add(new Processo(2, "Aristoteles", e, sem, rc));
        filosofos.add(new Processo(3, "Socrates", e, sem,rc));
        filosofos.add(new Processo(4, "Descartes", e, sem, rc));
        filosofos.add(new Processo(5, "Nietzsche", e, sem, rc));
        e.adiciona(filosofos);        
        e.start();
    }
}
