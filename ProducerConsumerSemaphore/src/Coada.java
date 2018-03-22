import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;

public class Coada {

    public int dimensiuneCoada;
    public static Queue<String> c = new LinkedList();
    private  Semaphore semFull;
    private  Semaphore semFree;

    Coada(int dimensiuneCoada){
        this.dimensiuneCoada = dimensiuneCoada;
        this.semFree  = new Semaphore(dimensiuneCoada);
        this.semFull = new Semaphore(0);
    }

    public void get() throws InterruptedException {
        semFull.acquire();
       synchronized (c) {
           c.remove();
       }
        System.out.println("Am luat un element din coada!");
        semFree.release();
        System.out.println("Se consuma elementul!");
    }


    public void put(String element) throws InterruptedException {
        System.out.println("Se produce un element");
        semFree.acquire();
        synchronized (c){
            c.add(element);
            System.out.println("Am adaugat un element in coada!");
        }
        semFull.release();
    }



}
