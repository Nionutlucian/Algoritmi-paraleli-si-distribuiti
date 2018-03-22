import java.util.concurrent.Semaphore;

public class Producer extends Thread {

    @Override
    public void run() {
        super.run();

        while (true) {
            try {
                TestProducerConsumer.coada.put("Element");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
