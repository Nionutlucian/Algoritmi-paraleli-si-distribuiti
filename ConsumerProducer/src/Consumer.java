import java.util.Queue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Consumer extends Thread {

    @Override
    public  void run() {
       while (true) {
           try {
               ProducerConsumerDemo.coada.take();
           } catch (InterruptedException e) {
               e.printStackTrace();
           }
       }
    }
}
