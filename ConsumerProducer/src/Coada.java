import java.util.LinkedList;
import java.util.Queue;

public class Coada {
    private Queue<String> coada = new LinkedList<>();
    private int dimensiuneCoada;

    Coada(int dimensiuneCoada){
        this.dimensiuneCoada = dimensiuneCoada;
    }

    public synchronized void put(String element) throws InterruptedException {
        while(coada.size() == dimensiuneCoada) {
            System.out.println("Coada este plina!");
            wait();
        }
        coada.add(element);
        System.out.println("Am adaugat un element in coada");
        notify();
    }

    public synchronized void take() throws InterruptedException {
        while(coada.isEmpty()) {
            System.out.println("Coada este goala!");
            wait();
        }
        coada.remove();
        System.out.println("Am sters un element din coada.");
        notify();
    }

}
