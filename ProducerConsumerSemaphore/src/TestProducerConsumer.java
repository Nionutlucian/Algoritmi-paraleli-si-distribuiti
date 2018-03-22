
public class TestProducerConsumer {

    public static Coada coada = new Coada(10);

    public static void main(String arrgs[]){

        for(int i = 0;i < 10 ; i++){
            Producer p = new Producer();
            Consumer c = new Consumer();
            p.start();
            c.start();
        }

    }
}
