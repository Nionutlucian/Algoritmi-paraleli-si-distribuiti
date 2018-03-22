import java.util.concurrent.Semaphore;

public class Consumer extends Thread {

    @Override
    public void run() {
        super.run();
        while(true){
            try {
               TestProducerConsumer.coada.get();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

    }
}
